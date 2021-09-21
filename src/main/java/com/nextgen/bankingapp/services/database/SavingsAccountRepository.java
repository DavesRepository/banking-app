package com.nextgen.bankingapp.services.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, String> {
  List<Account> findByUser(User user);
}
