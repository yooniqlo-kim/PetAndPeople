package com.ssafy.petandpeople.infrastructure.persistence.entity.post;

import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userKey", referencedColumnName = "userKey")
    private UserEntity userKey;

    private String title;

    private String content;

    private String animalType;

    private String breed;

    private String color;

    private String lastSeenLocation;

    private String contactInfo;

    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public PostEntity() {
    }

    public PostEntity(
            String title,
            String content,
            String animalType,
            String breed,
            String color,
            String lastSeenLocation,
            String contactInfo,
            String status) {
        this.title = title;
        this.content = content;
        this.animalType = animalType;
        this.breed = breed;
        this.color = color;
        this.lastSeenLocation = lastSeenLocation;
        this.contactInfo = contactInfo;
        this.status = status;
    }

    public PostEntity(
            UserEntity userKey,
            String title,
            String content,
            String animalType,
            String breed,
            String color,
            String lastSeenLocation,
            String contactInfo,
            String status) {
        this.userKey = userKey;
        this.title = title;
        this.content = content;
        this.animalType = animalType;
        this.breed = breed;
        this.color = color;
        this.lastSeenLocation = lastSeenLocation;
        this.contactInfo = contactInfo;
        this.status = status;
    }

    public Long getPostKey() {
        return postKey;
    }

    public UserEntity getUserKey() {
        return userKey;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getBreed() {
        return breed;
    }

    public String getColor() {
        return color;
    }

    public String getLastSeenLocation() {
        return lastSeenLocation;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setPostKey(Long postKey) {
        this.postKey = postKey;
    }

    public void setUserKey(UserEntity userKey) {
        this.userKey = userKey;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLastSeenLocation(String lastSeenLocation) {
        this.lastSeenLocation = lastSeenLocation;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
