package com.nextgen.bankingapp.services;

import com.nextgen.bankingapp.dto.TransferRequest;
import com.nextgen.bankingapp.services.database.CurrentAccount;
import com.nextgen.bankingapp.services.database.CurrentAccountRepository;
import com.nextgen.bankingapp.services.database.SavingsAccount;
import com.nextgen.bankingapp.services.database.SavingsAccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferServiceTest {

  @Test
  void shouldTransferMoneyFromCurrentToSavings() {
    CurrentAccountRepository currentAccountRepository = Mockito.mock(CurrentAccountRepository.class);
    SavingsAccountRepository savingsAccountRepository = Mockito.mock(SavingsAccountRepository.class);
    TransferService transferService = new TransferService(currentAccountRepository, savingsAccountRepository);

    CurrentAccount current = CurrentAccount.builder().accountNumber("58.44.23.14").balance(new BigDecimal("1600")).build();
    SavingsAccount savings = SavingsAccount.builder().accountNumber("12.33.10.14").balance(new BigDecimal("1600")).build();
    Mockito.when(currentAccountRepository.findById("58.44.23.14")).thenReturn(Optional.of(current));
    Mockito.when(savingsAccountRepository.findById("12.33.10.14")).thenReturn(Optional.of(savings));

    transferService.transfer(new TransferRequest("D.WEERNINK", "58.44.23.14", "12.33.10.14", 100));

    assertEquals(new BigDecimal("1500"), current.getBalance());
    assertEquals(new BigDecimal("1700"), savings.getBalance());
  }
}
