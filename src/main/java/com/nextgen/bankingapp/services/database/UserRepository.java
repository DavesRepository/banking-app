package com.nextgen.bankingapp.services.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

  Optional<Users> findByUsernameIgnoreCase(String username);
}
