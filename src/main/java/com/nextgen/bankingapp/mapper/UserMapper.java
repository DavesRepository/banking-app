package com.nextgen.bankingapp.mapper;

import com.nextgen.bankingapp.dto.UserDTO;
import com.nextgen.bankingapp.services.database.Users;

public interface UserMapper {
    UserDTO toDTO(Users users);
}
