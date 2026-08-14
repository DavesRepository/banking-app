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
        return accountNumber;
    }
}
