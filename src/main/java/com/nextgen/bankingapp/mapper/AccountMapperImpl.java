package com.nextgen.bankingapp.mapper;

import com.nextgen.bankingapp.dto.AccountDTO;
import com.nextgen.bankingapp.services.database.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDTO toDTO(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setBalance( account.getBalance() );

        accountDTO.setAccountNumber( maskAccountNumber(account.getAccountNumber()) );

        return accountDTO;
    }
}