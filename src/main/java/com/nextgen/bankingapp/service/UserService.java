package com.nextgen.bankingapp.service;

import com.nextgen.bankingapp.services.database.Users;

/**
 * Domain-level user lookups. Deliberately framework/transport agnostic so a
 * REST controller, a gRPC service, or a GraphQL resolver can all depend on
 * this single abstraction instead of duplicating lookup logic.
 */
public interface UserService {

  /**
   * @return the user for the given username.
   * @throws com.nextgen.bankingapp.exception.UserNotFoundException if no such user exists.
   */
  Users getUser(String username);
}
