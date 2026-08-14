package com.nextgen.bankingapp.mapper;

import com.nextgen.bankingapp.dto.AccountDTO;
import com.nextgen.bankingapp.services.database.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "accountNumber", expression = "java(maskAccountNumber(account.getAccountNumber()))")
    AccountDTO toDTO(Account account);

    default String maskAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isBlank()) {
            return accountNumber;
        }
        final String[] parts = accountNumber.split("\\.");
        final StringBuilder masked = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                masked.append(".");
            }
            masked.append(i == parts.length - 1 ? parts[i] : "**");
        }
        return masked.toString();
    }
}
