package com.ssafy.petandpeople.application.dto.user;

public class LoginDto {

    private String userId;

    private String userPassword;

    public LoginDto() {
    }

    public LoginDto(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

}
