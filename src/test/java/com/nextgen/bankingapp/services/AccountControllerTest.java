package com.nextgen.bankingapp.services;

import com.nextgen.bankingapp.services.database.Account;
import com.nextgen.bankingapp.services.database.CurrentAccount;
import com.nextgen.bankingapp.services.database.CurrentAccountRepository;
import com.nextgen.bankingapp.services.database.SavingsAccount;
import com.nextgen.bankingapp.services.database.SavingsAccountRepository;
import com.nextgen.bankingapp.mapper.AccountMapper;
import com.nextgen.bankingapp.mapper.UserMapper;
import com.nextgen.bankingapp.services.database.UserRepository;
import com.nextgen.bankingapp.services.database.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountControllerTest {

    @Test
    void shouldTestTotalAccountBalanceCalculatedCorrectly() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        CurrentAccountRepository currentAccountRepository = Mockito.mock(CurrentAccountRepository.class);
        SavingsAccountRepository savingsAccountRepository = Mockito.mock(SavingsAccountRepository.class);
        AccountMapper accountMapper = Mockito.mock(AccountMapper.class);
        UserMapper userMapper = Mockito.mock(UserMapper.class);

        AccountController accountController = new AccountController(
                currentAccountRepository, savingsAccountRepository, userRepository, accountMapper, userMapper);

        final Users userName = createUsers("UserWith2000Total");
        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(userName));

        List<Account> currentAccounts = createAccounts("1000.00", "250.00", "250.00");
        Mockito.when(currentAccountRepository.findByUsers(Mockito.any())).thenReturn(currentAccounts);

        List<Account> savingsAccounts = createAccounts("250.00", "250.00");
        Mockito.when(savingsAccountRepository.findByUsers(Mockito.any())).thenReturn(savingsAccounts);

        BigDecimal totalBalance = accountController.getTotalBalance("UserWith2000Total");

        Assertions.assertEquals(new BigDecimal("2000.00"), totalBalance);
    }

    private static Users createUsers(String userName) {
        return Users.builder()
                .username(userName)
                .build();
    }

    private static List<Account> createAccounts(String ... amounts) {
        ArrayList<Account> accounts = new ArrayList<>();
        for (String amount : amounts) {
            Account mockedAccount = Mockito.mock(Account.class);
            Mockito.when(mockedAccount.getBalance()).thenReturn(new BigDecimal(amount));
            accounts.add(mockedAccount);
        }
        return accounts;
    }
}
