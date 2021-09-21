package com.nextgen.bankingapp.services.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

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
