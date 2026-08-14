package com.nextgen.bankingapp.services.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentAccount implements Account {
  @Id
  private String accountNumber;

  @ManyToOne
  @JoinColumn(name = "username")
  private Users users;

  private BigDecimal balance;
}
