package com.ssafy.petandpeople.application.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class UserDto {

    @NotBlank(message = "ID는 필수 입력 사항입니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상, 20자 이하여야 하며, 영문, 숫자, 그리고 특수문자(!, @)를 반드시 포함해야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@])[a-zA-Z0-9!@]+$",
            message = "비밀번호는 8자 이상, 20자 이하여야 하며, 영문, 숫자, 그리고 특수문자(!, @)를 반드시 포함해야 합니다.")
    private String userPassword;

    @NotBlank(message = "이름은 필수 입력 사항입니다.")
    private String userName;

    @NotBlank(message = "휴대폰 번호는 필수 입력 사항입니다.")
    private String userPhoneNumber;

    @NotBlank(message = "주소는 필수 입력 사항입니다.")
    private String userAddress;

    private Date registeredAt;

    private Date lastLoginedAt;

    public UserDto() {
    }

    public UserDto(
            String userId,
            String userPassword,
            String userName,
            String userPhoneNumber,
            String userAddress,
            Date registeredAt,
            Date lastLoginedAt
    ) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userAddress = userAddress;
        this.registeredAt = registeredAt;
        this.lastLoginedAt = lastLoginedAt;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public Date getLastLoginedAt() {
        return lastLoginedAt;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public void setLastLoginedAt(Date lastLoginedAt) {
        this.lastLoginedAt = lastLoginedAt;
    }

}
