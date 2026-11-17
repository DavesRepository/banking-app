package com.nextgen.bankingapp.services;

import com.nextgen.bankingapp.services.database.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserSearchController {

  private final JdbcTemplate jdbcTemplate;

  @GetMapping("/users/search")
  public List<Users> searchByEmail(@RequestParam String email) {
    String sql = "SELECT * FROM users WHERE email LIKE '%" + email + "%'";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Users.class));
  }
}
