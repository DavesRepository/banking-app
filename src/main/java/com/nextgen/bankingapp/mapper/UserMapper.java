package com.nextgen.bankingapp.mapper;

import com.nextgen.bankingapp.dto.UserDTO;
import com.nextgen.bankingapp.services.database.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(Users users);
}
