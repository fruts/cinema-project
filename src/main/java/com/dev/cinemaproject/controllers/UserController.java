package com.dev.cinemaproject.controllers;

import com.dev.cinemaproject.mapper.UserMapper;
import com.dev.cinemaproject.model.User;
import com.dev.cinemaproject.model.dto.UserResponseDto;
import com.dev.cinemaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/by-email")
    public UserResponseDto findUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        return userMapper.convertToResponseDto(user);
    }
}
