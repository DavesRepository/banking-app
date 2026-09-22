package com.nextgen.bankingapp.services.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class CurrentAccount implements Account {
  @Id
  private String accountNumber;

  @ManyToOne
  @JoinColumn(name = "username")
  private Users users;

  private BigDecimal balance;

  public CurrentAccount(String accountNumber, Users users, BigDecimal balance) {
    this.accountNumber = accountNumber;
    this.users = users;
    this.balance = balance;
  }

  public CurrentAccount() {
  }

  public static CurrentAccountBuilder builder() {
    return new CurrentAccountBuilder();
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

  public static class CurrentAccountBuilder {
    private String accountNumber;
    private Users users;
    private BigDecimal balance;

    CurrentAccountBuilder() {
    }

    public CurrentAccountBuilder accountNumber(String accountNumber) {
      this.accountNumber = accountNumber;
      return this;
    }

    public CurrentAccountBuilder users(Users users) {
      this.users = users;
      return this;
    }

    public CurrentAccountBuilder balance(BigDecimal balance) {
      this.balance = balance;
      return this;
    }

    public CurrentAccount build() {
      return new CurrentAccount(this.accountNumber, this.users, this.balance);
    }

    public String toString() {
      return "CurrentAccount.CurrentAccountBuilder(accountNumber=" + this.accountNumber + ", users=" + this.users + ", balance=" + this.balance + ")";
    }
  }
}
