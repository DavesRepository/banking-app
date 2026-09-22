package com.nextgen.bankingapp.mapper;

import com.nextgen.bankingapp.dto.UserDTO;
import com.nextgen.bankingapp.services.database.Users;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-22T12:43:02+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25.0.2 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(Users users) {
        if ( users == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setUsername( users.getUsername() );
        userDTO.setEmail( users.getEmail() );

        return userDTO;
    }
}
