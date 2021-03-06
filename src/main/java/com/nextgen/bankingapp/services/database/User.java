package com.nextgen.bankingapp.services.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
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
