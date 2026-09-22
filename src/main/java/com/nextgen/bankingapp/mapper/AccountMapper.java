package com.nextgen.bankingapp.mapper;

import com.nextgen.bankingapp.dto.AccountDTO;
import com.nextgen.bankingapp.services.database.Account;

public interface AccountMapper {

    AccountDTO toDTO(Account account);

    default String maskAccountNumber(String accountNumber) {
        return accountNumber;
    }
}
