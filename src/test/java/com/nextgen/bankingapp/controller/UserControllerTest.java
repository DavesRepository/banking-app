package com.nextgen.bankingapp.controller;

import com.nextgen.bankingapp.dto.UserDTO;
import com.nextgen.bankingapp.exception.UserNotFoundException;
import com.nextgen.bankingapp.mapper.UserMapper;
import com.nextgen.bankingapp.service.UserService;
import com.nextgen.bankingapp.services.database.Users;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserControllerTest {

    @Test
    void getInfo_returnsOkWithUserDto() {
        final UserService userService = Mockito.mock(UserService.class);
        final UserMapper userMapper = Mockito.mock(UserMapper.class);

        final Users user = Users.builder().username("D.WEERNINK").email("dweernink@gmail.com").build();
        Mockito.when(userService.getUser("D.WEERNINK")).thenReturn(user);

        final UserDTO dto = new UserDTO();
        dto.setUsername("D.WEERNINK");
        dto.setEmail("dweernink@gmail.com");
        Mockito.when(userMapper.toDTO(user)).thenReturn(dto);

        final UserController controller = new UserController(userService, userMapper);
        final ResponseEntity<UserDTO> response = controller.getInfo("D.WEERNINK");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("D.WEERNINK", response.getBody().getUsername());
    }

    @Test
    void getInfo_propagatesUserNotFoundException() {
        final UserService userService = Mockito.mock(UserService.class);
        final UserMapper userMapper = Mockito.mock(UserMapper.class);
        Mockito.when(userService.getUser("ghost")).thenThrow(new UserNotFoundException("ghost"));

        final UserController controller = new UserController(userService, userMapper);

        // Not caught locally: GlobalExceptionHandler is responsible for turning this into a 404.
        assertThrows(UserNotFoundException.class, () -> controller.getInfo("ghost"));
    }
}
