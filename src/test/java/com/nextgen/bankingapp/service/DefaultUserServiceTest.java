package com.nextgen.bankingapp.service;

import com.nextgen.bankingapp.exception.UserNotFoundException;
import com.nextgen.bankingapp.services.database.UserRepository;
import com.nextgen.bankingapp.services.database.Users;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultUserServiceTest {

    @Test
    void getUser_returnsUserWhenFound() {
        final UserRepository userRepository = Mockito.mock(UserRepository.class);
        final Users user = Users.builder().username("D.WEERNINK").build();
        Mockito.when(userRepository.findByUsernameIgnoreCase("D.WEERNINK")).thenReturn(Optional.of(user));

        final DefaultUserService userService = new DefaultUserService(userRepository);

        assertEquals(user, userService.getUser("D.WEERNINK"));
    }

    @Test
    void getUser_isCaseInsensitive() {
        final UserRepository userRepository = Mockito.mock(UserRepository.class);
        final Users user = Users.builder().username("D.WEERNINK").build();
        Mockito.when(userRepository.findByUsernameIgnoreCase("d.weernink")).thenReturn(Optional.of(user));

        final DefaultUserService userService = new DefaultUserService(userRepository);

        assertEquals(user, userService.getUser("d.weernink"));
    }

    @Test
    void getUser_throwsUserNotFoundExceptionWhenMissing() {
        final UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findByUsernameIgnoreCase(Mockito.anyString())).thenReturn(Optional.empty());

        final DefaultUserService userService = new DefaultUserService(userRepository);

        assertThrows(UserNotFoundException.class, () -> userService.getUser("ghost"));
    }
}
