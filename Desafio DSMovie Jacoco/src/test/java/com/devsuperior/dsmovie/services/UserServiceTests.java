package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.entities.UserEntity;
import com.devsuperior.dsmovie.projections.UserDetailsProjection;
import com.devsuperior.dsmovie.repositories.UserRepository;
import com.devsuperior.dsmovie.tests.UserDetailsFactory;
import com.devsuperior.dsmovie.tests.UserFactory;
import com.devsuperior.dsmovie.utils.CustomUserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class UserServiceTests {

    UserEntity entity;

    @Mock
    UserRepository repository;

    @Mock
    CustomUserUtil util;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void setup() {
        this.entity = UserFactory.createUserEntity();
    }

    @Test
    public void authenticatedShouldReturnUserEntityWhenUserExists() {
        Mockito.when(this.util.getLoggedUsername()).thenReturn(entity.getUsername());
        Mockito.when(this.repository.findByUsername(entity.getUsername())).thenReturn(Optional.of(entity));

        UserEntity response = this.service.authenticated();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(entity.getUsername(), response.getUsername());
    }

    @Test
    public void authenticatedShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {
        Mockito.when(this.util.getLoggedUsername()).thenReturn("");
        Mockito.when(this.repository.findByUsername("")).thenThrow(UsernameNotFoundException.class);

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            this.service.authenticated();
        });
    }

    @Test
    public void loadUserByUsernameShouldReturnUserDetailsWhenUserExists() {
        Mockito.when(this.repository.searchUserAndRolesByUsername(entity.getUsername())).thenReturn(UserDetailsFactory.createCustomAdminUser(entity.getUsername()));

        UserDetails response = this.service.loadUserByUsername(entity.getUsername());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(entity.getUsername(), response.getUsername());
    }

    @Test
    public void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {
        Mockito.when(this.repository.searchUserAndRolesByUsername(ArgumentMatchers.anyString())).thenThrow(UsernameNotFoundException.class);

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            this.service.loadUserByUsername("");
        });
    }
}