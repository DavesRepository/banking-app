package com.nextgen.bankingapp.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private String accountNumber;
    private BigDecimal balance;
}
