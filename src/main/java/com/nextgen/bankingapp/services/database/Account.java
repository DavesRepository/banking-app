package com.nextgen.bankingapp.services.database;

import java.math.BigDecimal;

public interface Account {

  String getAccountNumber();

  BigDecimal getBalance();
}
