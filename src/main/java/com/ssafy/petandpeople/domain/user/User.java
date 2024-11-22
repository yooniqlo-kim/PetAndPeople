package com.ssafy.petandpeople.domain.user;

import java.util.Date;

public class User {

    private final String userId;

    private final Password password;

    private String userName;

    private String userPhoneNumber;

    private String userAddress;

    private Date registeredAt;

    private Date lastLoginAt;

    public User(String userId, Password password, String userName, String userPhoneNumber, String userAddress) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userAddress = userAddress;
    }

    public User(String userId, Password password, String userName, String userPhoneNumber, String userAddress, Date registeredAt, Date lastLoginAt) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userAddress = userAddress;
        this.registeredAt = registeredAt;
        this.lastLoginAt = lastLoginAt;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password.getValue();
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

    public void validatePasswordMatch(String encryptedLoginPassword) {
        password.validatePasswordMatch(encryptedLoginPassword);
    }

}
