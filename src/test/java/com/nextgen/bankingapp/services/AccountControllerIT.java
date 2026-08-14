package com.nextgen.bankingapp.services;

import com.nextgen.bankingapp.dto.AccountDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    final BigDecimal totalBalance1 = accountController.getTotalBalance("J.OTTO");
    assertEquals(totalBalance1, BigDecimal.valueOf(2600));

    final BigDecimal totalBalance2 = accountController.getTotalBalance("D.WEERNINK");
    assertEquals(totalBalance2, BigDecimal.valueOf(3200));
  }

  @Test
  void shouldReturnMaskedAccounts() {
    List<AccountDTO> accounts = accountController.getAccounts("D.WEERNINK");
    for (AccountDTO account : accounts) {
      assertTrue(account.getAccountNumber().equalsIgnoreCase("**.**.**.14"));
    }
  }

  @Disabled("Should fix this")
  @Test
  void shouldGetExceptionForNonExistingUser() {
    assertThrows(IllegalArgumentException.class,
            () -> accountController.getInfo("NonExistingUser"), "User with username NonExistingUser not found");
  }

}
