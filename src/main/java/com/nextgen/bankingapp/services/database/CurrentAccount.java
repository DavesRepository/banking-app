package com.nextgen.bankingapp.services.database;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CurrentAccount implements Account {
  @Id
  private String accountNumber;

  @ManyToOne
  @JoinColumn(name = "username")
  private User user;

  private BigDecimal balance;
}
