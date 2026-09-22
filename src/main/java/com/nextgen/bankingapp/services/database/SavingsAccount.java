package com.nextgen.bankingapp.services.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class SavingsAccount implements Account {
  @Id
  private String accountNumber;

  @ManyToOne
  @JoinColumn(name = "username")
  private Users users;

  private BigDecimal balance;

  public SavingsAccount(String accountNumber, Users users, BigDecimal balance) {
    this.accountNumber = accountNumber;
    this.users = users;
    this.balance = balance;
  }

  public SavingsAccount() {
  }

  public static SavingsAccountBuilder builder() {
    return new SavingsAccountBuilder();
  }

  public String getAccountNumber() {
    return this.accountNumber;
  }

  public Users getUsers() {
    return this.users;
  }

  public BigDecimal getBalance() {
    return this.balance;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public void setUsers(Users users) {
    this.users = users;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public static class SavingsAccountBuilder {
    private String accountNumber;
    private Users users;
    private BigDecimal balance;

    SavingsAccountBuilder() {
    }

    public SavingsAccountBuilder accountNumber(String accountNumber) {
      this.accountNumber = accountNumber;
      return this;
    }

    public SavingsAccountBuilder users(Users users) {
      this.users = users;
      return this;
    }

    public SavingsAccountBuilder balance(BigDecimal balance) {
      this.balance = balance;
      return this;
    }

    public SavingsAccount build() {
      return new SavingsAccount(this.accountNumber, this.users, this.balance);
    }

    public String toString() {
      return "SavingsAccount.SavingsAccountBuilder(accountNumber=" + this.accountNumber + ", users=" + this.users + ", balance=" + this.balance + ")";
    }
  }
}
