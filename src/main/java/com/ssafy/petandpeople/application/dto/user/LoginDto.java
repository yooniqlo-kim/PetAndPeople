package com.ssafy.petandpeople.application.dto.user;

public class LoginDto {

    public LoginDto() {
    }

    public LoginDto(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    private String userId;

    private String userPassword;

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

}
