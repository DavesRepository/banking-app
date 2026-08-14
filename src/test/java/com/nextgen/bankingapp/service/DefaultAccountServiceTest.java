package com.nextgen.bankingapp.service;

import com.nextgen.bankingapp.exception.UserNotFoundException;
import com.nextgen.bankingapp.services.database.Account;
import com.nextgen.bankingapp.services.database.CurrentAccountRepository;
import com.nextgen.bankingapp.services.database.SavingsAccountRepository;
import com.nextgen.bankingapp.services.database.Users;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Business-rule unit test, decoupled from Spring and from HTTP concerns -
 * this replaces the old AccountControllerTest, which tested the same
 * arithmetic but had to reach into AccountController via reflection/mocked
 * repositories to do it.
 */
class DefaultAccountServiceTest {

    @Test
    void getTotalBalance_sumsCurrentAndSavingsAccountBalances() {
        final CurrentAccountRepository currentAccountRepository = Mockito.mock(CurrentAccountRepository.class);
        final SavingsAccountRepository savingsAccountRepository = Mockito.mock(SavingsAccountRepository.class);
        final UserService userService = Mockito.mock(UserService.class);

        final Users user = createUser("UserWith2000Total");
        Mockito.when(userService.getUser("UserWith2000Total")).thenReturn(user);

        final List<Account> currentAccounts = createAccounts("1000.00", "250.00", "250.00");
        Mockito.when(currentAccountRepository.findByUsers(user)).thenReturn(currentAccounts);

        final List<Account> savingsAccounts = createAccounts("250.00", "250.00");
        Mockito.when(savingsAccountRepository.findByUsers(user)).thenReturn(savingsAccounts);

        final DefaultAccountService accountService =
                new DefaultAccountService(currentAccountRepository, savingsAccountRepository, userService);

        final BigDecimal totalBalance = accountService.getTotalBalance("UserWith2000Total");

        // current (1000 + 250 + 250 = 1500) + savings (250 + 250 = 500) = 2000
        assertEquals(new BigDecimal("2000.00"), totalBalance);
    }

    @Test
    void getAccounts_combinesCurrentAndSavingsAccounts() {
        final CurrentAccountRepository currentAccountRepository = Mockito.mock(CurrentAccountRepository.class);
        final SavingsAccountRepository savingsAccountRepository = Mockito.mock(SavingsAccountRepository.class);
        final UserService userService = Mockito.mock(UserService.class);

        final Users user = createUser("D.WEERNINK");
        Mockito.when(userService.getUser("D.WEERNINK")).thenReturn(user);

        final List<Account> currentAccounts = createAccounts("1600.00");
        final List<Account> savingsAccounts = createAccounts("1600.00");
        Mockito.when(currentAccountRepository.findByUsers(user)).thenReturn(currentAccounts);
        Mockito.when(savingsAccountRepository.findByUsers(user)).thenReturn(savingsAccounts);

        final DefaultAccountService accountService =
                new DefaultAccountService(currentAccountRepository, savingsAccountRepository, userService);

        final List<Account> accounts = accountService.getAccounts("D.WEERNINK");

        assertEquals(2, accounts.size());
    }

    @Test
    void getAccounts_propagatesUserNotFoundException() {
        final CurrentAccountRepository currentAccountRepository = Mockito.mock(CurrentAccountRepository.class);
        final SavingsAccountRepository savingsAccountRepository = Mockito.mock(SavingsAccountRepository.class);
        final UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userService.getUser("ghost")).thenThrow(new UserNotFoundException("ghost"));

        final DefaultAccountService accountService =
                new DefaultAccountService(currentAccountRepository, savingsAccountRepository, userService);

        assertThrows(UserNotFoundException.class, () -> accountService.getAccounts("ghost"));
    }

    private static Users createUser(String username) {
        return Users.builder()
                .username(username)
                .build();
    }

    private static List<Account> createAccounts(String... amounts) {
        final ArrayList<Account> accounts = new ArrayList<>();
        for (String amount : amounts) {
            final Account mockedAccount = Mockito.mock(Account.class);
            Mockito.when(mockedAccount.getBalance()).thenReturn(new BigDecimal(amount));
            accounts.add(mockedAccount);
        }
        return accounts;
    }
}
