package com.nextgen.bankingapp.services;

import com.nextgen.bankingapp.dto.AccountDTO;
import com.nextgen.bankingapp.dto.UserDTO;
import com.nextgen.bankingapp.mapper.AccountMapper;
import com.nextgen.bankingapp.mapper.UserMapper;
import com.nextgen.bankingapp.services.database.Account;
import com.nextgen.bankingapp.services.database.CurrentAccountRepository;
import com.nextgen.bankingapp.services.database.SavingsAccountRepository;
import com.nextgen.bankingapp.services.database.UserRepository;
import com.nextgen.bankingapp.services.database.Users;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AccountController {

  private final CurrentAccountRepository currentAccountRepository;
  private final SavingsAccountRepository savingsAccountRepository;
  private final UserRepository userRepository;
  private final AccountMapper accountMapper;
  private final UserMapper userMapper;

  public AccountController(CurrentAccountRepository currentAccountRepository,
                            SavingsAccountRepository savingsAccountRepository,
                            UserRepository userRepository,
                            AccountMapper accountMapper,
                            UserMapper userMapper) {
    this.currentAccountRepository = currentAccountRepository;
    this.savingsAccountRepository = savingsAccountRepository;
    this.userRepository = userRepository;
    this.accountMapper = accountMapper;
    this.userMapper = userMapper;
  }

  @GetMapping("/account/totalbalance/{username}")
  public BigDecimal getTotalBalance(@PathVariable(name = "username") String username) {
    final Optional<Users> user = userRepository.findById(username);
    if (user.isPresent()) {
      final List<Account> currentAccounts = currentAccountRepository.findByUsers(user.get());
      final BigDecimal totalCurrentAccountBalance = currentAccounts.stream().map(Account::getBalance).reduce(BigDecimal.ZERO, BigDecimal::add);

      final List<Account> savingsAccounts = savingsAccountRepository.findByUsers(user.get());
      final BigDecimal totalSavingsAccountBalance = savingsAccounts.stream().map(Account::getBalance).reduce(BigDecimal.ZERO, BigDecimal::add);

      return totalCurrentAccountBalance.add(totalSavingsAccountBalance);
    }
    return BigDecimal.ZERO;
  }

  @GetMapping("/accounts/{username}")
  public List<AccountDTO> getAccounts(@PathVariable(name = "username") String username) {
    final Optional<Users> user = userRepository.findById(username);
    if (user.isPresent()) {
      final List<Account> accounts = new ArrayList<>();
      accounts.addAll(currentAccountRepository.findByUsers(user.get()));
      accounts.addAll(savingsAccountRepository.findByUsers(user.get()));
      return accounts.stream().map(accountMapper::toDTO).collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

  @GetMapping("/user/{username}")
  public Optional<UserDTO> getInfo(@PathVariable(name = "username") String username) {
    List<Users> users = userRepository.findAll();

    for (Users user : users) {
      if (user.getUsername().equalsIgnoreCase(username)) {
        return Optional.of(userMapper.toDTO(user));
      }
    }
    return Optional.of(null);
  }
}
