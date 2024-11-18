package com.ssafy.petandpeople.infrastructure.persistence.entity.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user_security")
public class UserSecurityEntity {

    public UserSecurityEntity() {
    }

    public UserSecurityEntity(String userId, String salt) {
        this.userId = userId;
        this.salt = salt;
    }

    @Id
    @Column(unique = true, nullable = false)
    private String userId;

    @Column(unique = true, nullable = false)
    private String salt;

    public String getUserId() {
        return userId;
    }

    public String getSalt() {
        return salt;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
