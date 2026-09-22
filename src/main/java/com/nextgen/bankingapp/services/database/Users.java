package com.nextgen.bankingapp.services.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Users implements Serializable {
  @Id
  private String username;
  private String email;
  private String password;

  public Users(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public Users() {
  }

  public static UsersBuilder builder() {
    return new UsersBuilder();
  }

  public String getUsername() {
    return this.username;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public static class UsersBuilder {
    private String username;
    private String email;
    private String password;

    UsersBuilder() {
    }

    public UsersBuilder username(String username) {
      this.username = username;
      return this;
    }

    public UsersBuilder email(String email) {
      this.email = email;
      return this;
    }

    public UsersBuilder password(String password) {
      this.password = password;
      return this;
    }

    public Users build() {
      return new Users(this.username, this.email, this.password);
    }

    public String toString() {
      return "Users.UsersBuilder(username=" + this.username + ", email=" + this.email + ", password=" + this.password + ")";
    }
  }
}
