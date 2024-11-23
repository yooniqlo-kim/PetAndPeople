package com.ssafy.petandpeople.application.dto.post;

import java.time.LocalDateTime;

public class PostDto {

    private String title;
    private String content;
    private String animalType;
    private String breed;
    private String color;
    private String lastSeenLocation;
    private String contactInfo;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String thumbnailPath;

    public PostDto() {
    }

    public PostDto(
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

    public PostDto(
            String title,
            String content,
            String animalType,
            String breed,
            String color,
            String lastSeenLocation,
            String contactInfo,
            String status,
            LocalDateTime updatedAt,
            String thumbnailPath) {
        this.title = title;
        this.content = content;
        this.animalType = animalType;
        this.breed = breed;
        this.color = color;
        this.lastSeenLocation = lastSeenLocation;
        this.contactInfo = contactInfo;
        this.status = status;
        this.updatedAt = updatedAt;
        this.thumbnailPath = thumbnailPath;
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

    public String getThumbnailPath() {
        return thumbnailPath;
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

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

}
