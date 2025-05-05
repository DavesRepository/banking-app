package com.nextgen.bankingapp.services.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class SavingsAccount implements Account {
  @Id
  private String accountNumber;

  @ManyToOne
  @JoinColumn(name = "username")
  private User user;

  private BigDecimal balance;
}
