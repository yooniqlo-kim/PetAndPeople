package com.ssafy.petandpeople.domain.user;

import java.util.Date;

public class User {

    public User(String userId,
                Password password,
                String userName,
                String userPhoneNumber,
                String userAddress,
                Date registeredAt,
                Date lastLoginedAt
    ) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userAddress = userAddress;
        this.registeredAt = registeredAt;
        this.lastLoginedAt = lastLoginedAt;
    }

    private String userId;

    private Password password;

    private String userName;

    private String userPhoneNumber;

    private String userAddress;

    private Date registeredAt;

    private Date lastLoginedAt;

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

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public Date getLastLoginedAt() {
        return lastLoginedAt;
    }

    public void encryptPassword(String salt) {
        this.password = Password.encrypt(salt, this.password);
    }

}
