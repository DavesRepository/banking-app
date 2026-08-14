package com.nextgen.bankingapp.controller;

import com.nextgen.bankingapp.dto.UserDTO;
import com.nextgen.bankingapp.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserControllerIT {

  @Autowired
  private UserController userController;

  @Test
  void getInfo_returnsUserForExistingUsername() {
    final ResponseEntity<UserDTO> response = userController.getInfo("D.WEERNINK");

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("D.WEERNINK", response.getBody().getUsername());
    assertEquals("dweernink@gmail.com", response.getBody().getEmail());
  }

  @Test
  void getInfo_isCaseInsensitive() {
    final ResponseEntity<UserDTO> response = userController.getInfo("d.weernink");

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("D.WEERNINK", response.getBody().getUsername());
  }

  @Test
  void getInfo_throwsUserNotFoundExceptionForUnknownUser() {
    // BA-1246 follow-up: previously threw NullPointerException (Optional.of(null)) and was
    // @Disabled("Should fix this"). UserService now throws a typed UserNotFoundException,
    // which GlobalExceptionHandler turns into a 404 at the HTTP layer.
    assertThrows(UserNotFoundException.class, () -> userController.getInfo("NonExistingUser"));
  }
}
