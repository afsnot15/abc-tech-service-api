package br.com.fiap.abctechservice.service.impl;

import br.com.fiap.abctechservice.application.dto.AuthDto;
import br.com.fiap.abctechservice.application.dto.JwtDto;
import br.com.fiap.abctechservice.application.dto.UserCreateDto;
import br.com.fiap.abctechservice.application.dto.UserDto;
import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.model.Operator;
import br.com.fiap.abctechservice.repository.AssistanceRepository;
import br.com.fiap.abctechservice.repository.OperatorRepository;
import br.com.fiap.abctechservice.repository.OrderRepository;
import br.com.fiap.abctechservice.security.JwtTokenUtil;
import br.com.fiap.abctechservice.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private OperatorRepository userRepository;

    private UserServiceImpl userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        userService = new UserServiceImpl(jwtTokenUtil, authenticationManager, passwordEncoder, userRepository);
    }

    @Test
    void create() {
        UserDto userExpected = new UserDto();
        userExpected.setId(0L);
        userExpected.setName("userCreated");
        userExpected.setRegistration("123456");

        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setName("MockName");
        userCreateDto.setPassword("123456");

        when(passwordEncoder.encode(any())).thenReturn("123456");
        when(userRepository.save(any())).thenReturn(new Operator(1L, "userCreated", "123456", "123456"));

        UserDto userActual = userService.create(new UserCreateDto());

        Assertions.assertEquals(userExpected.getName(), userActual.getName());
        Assertions.assertTrue(Long.parseLong(userExpected.getRegistration()) > 0);
    }

    @Test
    void login() {
        when(jwtTokenUtil.generateToken(any())).thenReturn("123456");

        JwtDto jwtActual = userService.login(new AuthDto());

        Assertions.assertEquals("123456", jwtActual.getToken());
    }
}