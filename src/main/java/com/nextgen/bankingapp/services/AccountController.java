package com.nextgen.bankingapp.services;

import com.nextgen.bankingapp.services.database.Account;
import com.nextgen.bankingapp.services.database.CurrentAccountRepository;
import com.nextgen.bankingapp.services.database.SavingsAccountRepository;
import com.nextgen.bankingapp.services.database.User;
import com.nextgen.bankingapp.services.database.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class AccountController {
  @Autowired
  private CurrentAccountRepository currentAccountRepository;
  @Autowired
  private SavingsAccountRepository savingsAccountRepository;
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/account/totalbalance/{username}")
  public BigDecimal getTotalBalance(@PathVariable(name = "username")  String username) {
    final Optional<User> user = userRepository.findById(username);
    if (user.isPresent()) {
      final List<Account> currentAccounts =  currentAccountRepository.findByUser(user.get());
      final BigDecimal totalCurrentAccountBalance = currentAccounts.stream().map(Account::getBalance).reduce(BigDecimal.ZERO, BigDecimal::add);

      final List<Account> savingsAccounts = currentAccountRepository.findByUser(user.get());
      final BigDecimal totalSavingsAccountBalance = savingsAccounts.stream().map(Account::getBalance).reduce(BigDecimal.ZERO, BigDecimal::add);

      return totalCurrentAccountBalance.add(totalSavingsAccountBalance);
    }
    return BigDecimal.ZERO;
  }

  @GetMapping("/user/{username}")
  public User getInfo(@PathVariable(name = "username")  String username){
    final Optional<User> user = userRepository.findById(username);
    return user.orElse(null);
  }
}
