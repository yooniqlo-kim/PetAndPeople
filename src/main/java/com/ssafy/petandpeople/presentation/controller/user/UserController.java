package com.ssafy.petandpeople.presentation.controller.user;

import com.ssafy.petandpeople.application.dto.user.LoginDto;
import com.ssafy.petandpeople.application.dto.user.UserDto;
import com.ssafy.petandpeople.application.service.user.UserService;
import com.ssafy.petandpeople.presentation.response.Api;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/check/password")
    private Api<Object> checkPassword(@RequestBody LoginDto loginDto) {
        userService.checkPasswordMatch(loginDto);

        return Api.OK();
    }

    @PostMapping("/detail")
    public Api<UserDto> getDetailAboutUser(HttpServletRequest request) {
        UserDto userDto = userService.getDetailAboutUser(request);

        return Api.OK(userDto);
    }

    @PostMapping("/logout")
    public Api<Object> logout(HttpServletRequest request) {
        userService.logout(request);

        return Api.OK();
    }

    @GetMapping("/check")
    public Api<Object> checkUserSession(HttpServletRequest request) {
        userService.checkUserSession(request);

        return Api.OK();
    }

}