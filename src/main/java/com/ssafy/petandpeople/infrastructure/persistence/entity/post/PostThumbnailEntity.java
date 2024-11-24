package com.ssafy.petandpeople.infrastructure.persistence.entity.post;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "post_thumbnail")
public class PostThumbnailEntity {

    @Id
    private String thumbnailKey;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postKey", referencedColumnName = "postKey")
    private PostEntity postKey;

    private String originalFileName;

    private String s3FileName;

    private String thumbnailPath;

    @Transient
    private String extension;

    public PostThumbnailEntity() {
    }

    public PostThumbnailEntity(String thumbnailKey, PostEntity postKey, String originalFileName, String s3FileName, String extension) {
        this.thumbnailKey = thumbnailKey;
        this.postKey = postKey;
        this.originalFileName = originalFileName;
        this.s3FileName = s3FileName;
        this.extension = extension;
    }

    public String getThumbnailKey() {
        return thumbnailKey;
    }

    public PostEntity getPostKey() {
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

    public String getExtension() {
        return extension;
    }

    public void setThumbnailKey(String thumbnailKey) {
        this.thumbnailKey = thumbnailKey;
    }

    public void setPostKey(PostEntity postKey) {
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
