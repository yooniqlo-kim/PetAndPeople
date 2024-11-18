package com.ssafy.petandpeople.presentation.controller.user;

import com.ssafy.petandpeople.application.dto.user.LoginDto;
import com.ssafy.petandpeople.application.dto.user.UserDto;
import com.ssafy.petandpeople.application.service.user.UserService;
import com.ssafy.petandpeople.presentation.response.Api;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public Api<Object> signUp(@Valid @RequestBody UserDto userDto) {
        userService.signUp(userDto);

        return Api.OK();
    }

    @PostMapping("/login")
    public Api<Object> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        userService.login(loginDto, request);

        return Api.OK();
    }

}
