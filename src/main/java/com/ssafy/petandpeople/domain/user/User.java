package com.ssafy.petandpeople.domain.user;

import java.util.Date;

public class User {

    private String userId;

    private Password password;

    private String userName;

    private String userPhoneNumber;

    private String userAddress;

    private Date registeredAt;

    private Date lastLoginedAt;

    public User(String userId, Password password, String userName, String userPhoneNumber, String userAddress, Date registeredAt, Date lastLoginedAt) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userAddress = userAddress;
        this.registeredAt = registeredAt;
        this.lastLoginedAt = lastLoginedAt;
    }

    public User(String userId, Password password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public Password getPassword() {
        return password;
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

    public void encryptPassword(String salt) {
        this.password = this.password.encrypt(salt);
    }

    public void validatePasswordMatch(String savedPassword) {
        this.password.validate(savedPassword);
    }
}
