package com.ssafy.petandpeople.infrastructure.persistence.entity.job.parttime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "part_time_thumbnail")
public class PartTimePostThumbnailEntity {

    @Id
    private String thumbnailKey;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postKey", referencedColumnName = "postKey")
    private PartTimePostEntity postKey;

    private String originalFileName;

    private String s3FileName;

    private String thumbnailPath;

    public PartTimePostThumbnailEntity() {
    }

    public PartTimePostThumbnailEntity(String thumbnailKey, PartTimePostEntity postKey, String originalFileName, String s3FileName, String thumbnailPath) {
        this.thumbnailKey = thumbnailKey;
        this.postKey = postKey;
        this.originalFileName = originalFileName;
        this.s3FileName = s3FileName;
        this.thumbnailPath = thumbnailPath;
    }

    public String getThumbnailKey() {
        return thumbnailKey;
    }

    public PartTimePostEntity getPostKey() {
        return postKey;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getS3FileName() {
        return s3FileName;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailKey(String thumbnailKey) {
        this.thumbnailKey = thumbnailKey;
    }

    public void setPostKey(PartTimePostEntity postKey) {
        this.postKey = postKey;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public void setS3FileName(String s3FileName) {
        this.s3FileName = s3FileName;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

}
