package com.nextgen.bankingapp.controller;

import com.nextgen.bankingapp.dto.AccountDTO;
import com.nextgen.bankingapp.dto.TotalBalanceDTO;
import com.nextgen.bankingapp.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AccountControllerIT {

  @Autowired
  private AccountController accountController;

  @Test
  void getTotalBalance() {
    final ResponseEntity<TotalBalanceDTO> response1 = accountController.getTotalBalance("J.OTTO");
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals(BigDecimal.valueOf(2600), response1.getBody().getTotalBalance());

    final ResponseEntity<TotalBalanceDTO> response2 = accountController.getTotalBalance("D.WEERNINK");
    assertEquals(HttpStatus.OK, response2.getStatusCode());
    assertEquals(BigDecimal.valueOf(3200), response2.getBody().getTotalBalance());
  }

  @Test
  void getTotalBalance_throwsUserNotFoundExceptionForUnknownUser() {
    assertThrows(UserNotFoundException.class, () -> accountController.getTotalBalance("NonExistingUser"));
  }

  @Test
  void shouldReturnMaskedAccounts() {
    final ResponseEntity<List<AccountDTO>> response = accountController.getAccounts("D.WEERNINK");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    for (AccountDTO account : response.getBody()) {
      assertTrue(account.getAccountNumber().equalsIgnoreCase("**.**.**.14"));
    }
  }

  @Test
  void getAccounts_throwsUserNotFoundExceptionForUnknownUser() {
    assertThrows(UserNotFoundException.class, () -> accountController.getAccounts("NonExistingUser"));
  }
}
