package br.com.fiap.abctechservice.service;

import br.com.fiap.abctechservice.application.dto.AuthDto;
import br.com.fiap.abctechservice.application.dto.JwtDto;
import br.com.fiap.abctechservice.application.dto.UserCreateDto;
import br.com.fiap.abctechservice.application.dto.UserDto;

public interface UserService {
    UserDto create(UserCreateDto userCreateDTO);

    JwtDto login(AuthDto authDTO);
}