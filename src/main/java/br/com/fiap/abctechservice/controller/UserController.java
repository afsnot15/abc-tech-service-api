package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.application.dto.AuthDto;
import br.com.fiap.abctechservice.application.dto.JwtDto;
import br.com.fiap.abctechservice.application.dto.UserCreateDto;
import br.com.fiap.abctechservice.application.dto.UserDto;
import br.com.fiap.abctechservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserCreateDto userCreateDto){
        return userService.create(userCreateDto);
    }

    @PostMapping("login")
    public JwtDto login(@RequestBody AuthDto authDTO){
        return userService.login(authDTO);
    }
}