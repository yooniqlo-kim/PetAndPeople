package com.ssafy.petandpeople.application.dto;

public class PartTimeDto {

    public PartTimeDto() {
    }

    public PartTimeDto(
            String postTitle,
            String partTimeDeadline,
            String partTimeCount,
            String partTimeSalary,
            String partTimeAge,
            String partTimePeriod,
            String partTimeDays,
            String partTimeHours,
            String partTimeAddress,
            String postContent,
            String managerName,
            String managerPhoneNumber
    ) {
        this.postTitle = postTitle;
        this.partTimeDeadline = partTimeDeadline;
        this.partTimeCount = partTimeCount;
        this.partTimeSalary = partTimeSalary;
        this.partTimeAge = partTimeAge;
        this.partTimePeriod = partTimePeriod;
        this.partTimeDays = partTimeDays;
        this.partTimeHours = partTimeHours;
        this.partTimeAddress = partTimeAddress;
        this.postContent = postContent;
        this.managerName = managerName;
        this.managerPhoneNumber = managerPhoneNumber;
    }

    private String postTitle;

    private String partTimeDeadline;

    private String partTimeCount;

    private String partTimeSalary;

    private String partTimeAge;

    private String partTimePeriod;

    private String partTimeDays;

    private String partTimeHours;

    private String partTimeAddress;

    private String postContent;

    private String managerName;

    private String managerPhoneNumber;

    public String getPostTitle() {
        return postTitle;
    }

    public String getPartTimeDeadline() {
        return partTimeDeadline;
    }

    public String getPartTimeCount() {
        return partTimeCount;
    }

    public String getPartTimeSalary() {
        return partTimeSalary;
    }

    public String getPartTimeAge() {
        return partTimeAge;
    }

    public String getPartTimePeriod() {
        return partTimePeriod;
    }

    public String getPartTimeDays() {
        return partTimeDays;
    }

    public String getPartTimeHours() {
        return partTimeHours;
    }

    public String getPartTimeAddress() {
        return partTimeAddress;
    }

    public String getPostContent() {
        return postContent;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setPartTimeDeadline(String partTimeDeadline) {
        this.partTimeDeadline = partTimeDeadline;
    }

    public void setPartTimeCount(String partTimeCount) {
        this.partTimeCount = partTimeCount;
    }

    public void setPartTimeSalary(String partTimeSalary) {
        this.partTimeSalary = partTimeSalary;
    }

    public void setPartTimeAge(String partTimeAge) {
        this.partTimeAge = partTimeAge;
    }

    public void setPartTimePeriod(String partTimePeriod) {
        this.partTimePeriod = partTimePeriod;
    }

    public void setPartTimeDays(String partTimeDays) {
        this.partTimeDays = partTimeDays;
    }

    public void setPartTimeHours(String partTimeHours) {
        this.partTimeHours = partTimeHours;
    }

    public void setPartTimeAddress(String partTimeAddress) {
        this.partTimeAddress = partTimeAddress;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public void setManagerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
    }

}
