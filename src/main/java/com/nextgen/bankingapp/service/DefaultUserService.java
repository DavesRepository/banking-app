package com.nextgen.bankingapp.service;

import com.nextgen.bankingapp.exception.UserNotFoundException;
import com.nextgen.bankingapp.services.database.UserRepository;
import com.nextgen.bankingapp.services.database.Users;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

  private final UserRepository userRepository;

  public DefaultUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Users getUser(String username) {
    return userRepository.findByUsernameIgnoreCase(username)
        .orElseThrow(() -> new UserNotFoundException(username));
  }
}
