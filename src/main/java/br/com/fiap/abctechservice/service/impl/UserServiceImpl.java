package br.com.fiap.abctechservice.service.impl;

import br.com.fiap.abctechservice.application.dto.AuthDto;
import br.com.fiap.abctechservice.application.dto.JwtDto;
import br.com.fiap.abctechservice.application.dto.UserCreateDto;
import br.com.fiap.abctechservice.application.dto.UserDto;
import br.com.fiap.abctechservice.model.Operator;
import br.com.fiap.abctechservice.repository.OperatorRepository;
import br.com.fiap.abctechservice.security.JwtTokenUtil;
import br.com.fiap.abctechservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private OperatorRepository userRepository;

    public UserServiceImpl(JwtTokenUtil jwtTokenUtil,
                           AuthenticationManager authenticationManager,
                           PasswordEncoder passwordEncoder,
                           OperatorRepository userRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(UserCreateDto userCreateDTO) {
        Operator user = new Operator();
        user.setName(userCreateDTO.getName());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setRegistration(generateRegistration());

        Operator savedUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setRegistration(savedUser.getRegistration());
        userDto.setName(savedUser.getName());
        return userDto;
    }

    @Override
    public JwtDto login(AuthDto authDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDTO.getRegistration(), authDTO.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        String token = jwtTokenUtil.generateToken(authDTO.getRegistration());
        JwtDto jwtDTO = new JwtDto();
        jwtDTO.setToken(token);

        Optional<Operator> operator = userRepository.findFirstByRegistration(authDTO.getRegistration());
        if (operator.isPresent()) {
            jwtDTO.setOperator(operator.get().getName());
        }

        return jwtDTO;
    }

    private String generateRegistration() {
        return String.valueOf(new Random().nextInt() & Integer.MAX_VALUE);
    }
}