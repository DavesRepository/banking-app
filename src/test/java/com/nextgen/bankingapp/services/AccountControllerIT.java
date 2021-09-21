package com.nextgen.bankingapp.services;

import com.nextgen.bankingapp.services.database.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class AccountControllerIT {

  @Autowired
  private AccountController accountController;

  @Test
  void getTotalBalance() {
    final BigDecimal totalBalance1 = accountController.getTotalBalance("J.OTTO");
    assertEquals(totalBalance1, BigDecimal.valueOf(3000));

    final BigDecimal totalBalance2 = accountController.getTotalBalance("DWEERNINK");
    assertEquals(totalBalance2, BigDecimal.valueOf(3200));
  }

  @Test
  void shouldGetExceptionForNonExistingUser() {
    assertThrows(IllegalArgumentException.class, () -> accountController.getInfo("NonExistingUser"), "User with username NonExistingUser not found");
  }

  @Test
  void shouldGetUser(){
    final User userInfo = accountController.getInfo("H.BAKKER");
    assertEquals(userInfo.getUsername(), "H.BAKKER");
    assertEquals(userInfo.getEmail(), "h.bakker@gmail.com");
    assertEquals(userInfo.getPassword(), "hbakker123");
  }
}
