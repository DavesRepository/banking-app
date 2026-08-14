package com.nextgen.bankingapp.service;

import com.nextgen.bankingapp.services.database.Account;

import java.math.BigDecimal;
import java.util.List;

/**
 * Owns all account business rules (aggregation, totalling). Anything that
 * needs "the accounts for a user" or "the total balance for a user" - a REST
 * controller today, a gRPC service or GraphQL resolver tomorrow - should
 * depend on this interface rather than talking to the repositories directly.
 */
public interface AccountService {

  /**
   * @return the combined current + savings accounts for the given user.
   * @throws com.nextgen.bankingapp.exception.UserNotFoundException if no such user exists.
   */
  List<Account> getAccounts(String username);

  /**
   * @return the sum of the balances of every current and savings account for the given user.
   * @throws com.nextgen.bankingapp.exception.UserNotFoundException if no such user exists.
   */
  BigDecimal getTotalBalance(String username);
}
