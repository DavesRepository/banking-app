package com.nextgen.bankingapp.service;

import com.nextgen.bankingapp.services.database.Account;
import com.nextgen.bankingapp.services.database.CurrentAccountRepository;
import com.nextgen.bankingapp.services.database.SavingsAccountRepository;
import com.nextgen.bankingapp.services.database.Users;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultAccountService implements AccountService {

  private final CurrentAccountRepository currentAccountRepository;
  private final SavingsAccountRepository savingsAccountRepository;
  private final UserService userService;

  public DefaultAccountService(CurrentAccountRepository currentAccountRepository,
                                SavingsAccountRepository savingsAccountRepository,
                                UserService userService) {
    this.currentAccountRepository = currentAccountRepository;
    this.savingsAccountRepository = savingsAccountRepository;
    this.userService = userService;
  }

  @Override
  public List<Account> getAccounts(String username) {
    final Users user = userService.getUser(username);
    final List<Account> accounts = new ArrayList<>(currentAccountRepository.findByUsers(user));
    accounts.addAll(savingsAccountRepository.findByUsers(user));
    return accounts;
  }

  @Override
  public BigDecimal getTotalBalance(String username) {
    return getAccounts(username).stream()
        .map(Account::getBalance)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
