package com.nextgen.bankingapp.controller;

import com.nextgen.bankingapp.dto.UserDTO;
import com.nextgen.bankingapp.mapper.UserMapper;
import com.nextgen.bankingapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Split out of AccountController: user lookups are a different concern from
 * account/balance data and don't belong on the same controller just because
 * they happen to share a data source.
 */
@RestController
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @GetMapping("/user/{username}")
  public ResponseEntity<UserDTO> getInfo(@PathVariable(name = "username") String username) {
    final UserDTO userDTO = userMapper.toDTO(userService.getUser(username));
    return ResponseEntity.ok(userDTO);
  }
}
