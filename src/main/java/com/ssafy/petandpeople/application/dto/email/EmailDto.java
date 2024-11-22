package com.ssafy.petandpeople.application.dto.email;

public class EmailDto {

    private String email;

    private String authCode;

    public String getEmail() {
        return email;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

}
