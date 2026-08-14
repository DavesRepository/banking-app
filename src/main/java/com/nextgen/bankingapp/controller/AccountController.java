package com.nextgen.bankingapp.controller;

import com.nextgen.bankingapp.dto.AccountDTO;
import com.nextgen.bankingapp.dto.TotalBalanceDTO;
import com.nextgen.bankingapp.mapper.AccountMapper;
import com.nextgen.bankingapp.service.AccountService;
import com.nextgen.bankingapp.services.database.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Pure HTTP adapter: parses the request, delegates to {@link AccountService}
 * for every business rule, maps the result to a DTO, and picks the status
 * code. No aggregation/summing/masking logic lives here - see
 * {@link AccountService} and {@link AccountMapper} for that. A "user not
 * found" here isn't handled locally either; {@link AccountService} throws
 * {@code UserNotFoundException}, which {@code GlobalExceptionHandler} turns
 * into a 404 for every endpoint uniformly.
 */
@RestController
public class AccountController {

  private final AccountService accountService;
  private final AccountMapper accountMapper;

  public AccountController(AccountService accountService, AccountMapper accountMapper) {
    this.accountService = accountService;
    this.accountMapper = accountMapper;
  }

  @GetMapping("/accounts/{username}")
  public ResponseEntity<List<AccountDTO>> getAccounts(@PathVariable(name = "username") String username) {
    final List<Account> accounts = accountService.getAccounts(username);
    final List<AccountDTO> accountDTOs = accounts.stream()
        .map(accountMapper::toDTO)
        .collect(Collectors.toList());
    return ResponseEntity.ok(accountDTOs);
  }

  @GetMapping("/account/totalbalance/{username}")
  public ResponseEntity<TotalBalanceDTO> getTotalBalance(@PathVariable(name = "username") String username) {
    final var totalBalance = accountService.getTotalBalance(username);
    return ResponseEntity.ok(new TotalBalanceDTO(username, totalBalance));
  }
}
