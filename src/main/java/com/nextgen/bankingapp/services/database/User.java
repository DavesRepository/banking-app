package com.nextgen.bankingapp.services.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class User implements Serializable {
  @Id
  private String username;
  private String email;
  private String password;

}
