package com.nextgen.bankingapp.controller;

import com.nextgen.bankingapp.dto.AccountDTO;
import com.nextgen.bankingapp.dto.TotalBalanceDTO;
import com.nextgen.bankingapp.exception.UserNotFoundException;
import com.nextgen.bankingapp.mapper.AccountMapper;
import com.nextgen.bankingapp.service.AccountService;
import com.nextgen.bankingapp.services.database.Account;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests that AccountController does its one job correctly: delegate to
 * AccountService, map the result, and return the right status code. No
 * business arithmetic is asserted here - that belongs to
 * DefaultAccountServiceTest.
 */
class AccountControllerTest {

    @Test
    void getTotalBalance_returnsOkWithTotalBalanceDto() {
        final AccountService accountService = Mockito.mock(AccountService.class);
        final AccountMapper accountMapper = Mockito.mock(AccountMapper.class);
        Mockito.when(accountService.getTotalBalance("D.WEERNINK")).thenReturn(new BigDecimal("3200"));

        final AccountController controller = new AccountController(accountService, accountMapper);
        final ResponseEntity<TotalBalanceDTO> response = controller.getTotalBalance("D.WEERNINK");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("D.WEERNINK", response.getBody().getUsername());
        assertEquals(new BigDecimal("3200"), response.getBody().getTotalBalance());
    }

    @Test
    void getTotalBalance_propagatesUserNotFoundException() {
        final AccountService accountService = Mockito.mock(AccountService.class);
        final AccountMapper accountMapper = Mockito.mock(AccountMapper.class);
        Mockito.when(accountService.getTotalBalance("ghost")).thenThrow(new UserNotFoundException("ghost"));

        final AccountController controller = new AccountController(accountService, accountMapper);

        // Not caught locally: GlobalExceptionHandler is responsible for turning this into a 404.
        assertThrows(UserNotFoundException.class, () -> controller.getTotalBalance("ghost"));
    }

    @Test
    void getAccounts_mapsEachAccountAndReturnsOk() {
        final AccountService accountService = Mockito.mock(AccountService.class);
        final AccountMapper accountMapper = Mockito.mock(AccountMapper.class);

        final Account account = Mockito.mock(Account.class);
        Mockito.when(accountService.getAccounts("D.WEERNINK")).thenReturn(List.of(account));

        final AccountDTO dto = new AccountDTO();
        dto.setAccountNumber("**.**.**.14");
        dto.setBalance(new BigDecimal("1600"));
        Mockito.when(accountMapper.toDTO(account)).thenReturn(dto);

        final AccountController controller = new AccountController(accountService, accountMapper);
        final ResponseEntity<List<AccountDTO>> response = controller.getAccounts("D.WEERNINK");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("**.**.**.14", response.getBody().get(0).getAccountNumber());
    }
}
