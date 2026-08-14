package com.nextgen.bankingapp.mapper;

import com.nextgen.bankingapp.dto.AccountDTO;
import com.nextgen.bankingapp.service.AccountNumberMasker;
import com.nextgen.bankingapp.services.database.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = AccountNumberMasker.class)
public interface AccountMapper {

    @Mapping(target = "accountNumber", expression = "java(AccountNumberMasker.mask(account.getAccountNumber()))")
    AccountDTO toDTO(Account account);
}
