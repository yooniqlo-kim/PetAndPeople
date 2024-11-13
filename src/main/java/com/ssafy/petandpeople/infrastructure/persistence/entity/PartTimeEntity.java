package com.ssafy.petandpeople.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "part_time_post")
public class PartTimeEntity {

    public PartTimeEntity() {
    }

    public PartTimeEntity(
            UserEntity userKey,
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
        this.userKey = userKey;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userKey", referencedColumnName = "userKey")
    private UserEntity userKey;

    @Column(nullable = false)
    private String postTitle;

    @Column(nullable = false)
    private String partTimeDeadline;

    @Column(nullable = false)
    private String partTimeCount;

    @Column(nullable = false)
    private String partTimeSalary;

    @Column(nullable = false)
    private String partTimeAge;

    @Column(nullable = false)
    private String partTimePeriod;

    @Column(nullable = false)
    private String partTimeDays;

    @Column(nullable = false)
    private String partTimeHours;

    @Column(nullable = false)
    private String partTimeAddress;

    @Column(nullable = false)
    private String postContent;

    @Column(nullable = false)
    private String managerName;

    @Column(nullable = false)
    private String managerPhoneNumber;

    public Long getPostKey() {
        return postKey;
    }

    public UserEntity getUserKey() {
        return userKey;
    }

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

}
