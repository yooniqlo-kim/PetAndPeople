package com.ssafy.petandpeople.domain.post;

import com.ssafy.petandpeople.common.exception.post.InvalidThumbnailExtensionException;
import com.ssafy.petandpeople.common.exception.post.OriginalFileNameEmptyException;

public class FileName {

    private final String originalFileName;
    private final String extension;

    public FileName(String originalFileName) {
        validateOriginalFileName(originalFileName);
        this.originalFileName = originalFileName;
        this.extension = extractExtension(originalFileName);
    }

    private void validateOriginalFileName(String originalFileName) {
        if (originalFileName == null || originalFileName.isEmpty()) {
            throw new OriginalFileNameEmptyException();
        }
    }

    private String extractExtension(String originalFileName) {
        int lastDotIndex = originalFileName.lastIndexOf(".");

        return originalFileName.substring(lastDotIndex + 1);
    }

    private void validateExtension(int lastDotIndex) {
        if (lastDotIndex == -1 || lastDotIndex == originalFileName.length() - 1) {
            throw new InvalidThumbnailExtensionException();
        }
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getExtension() {
        return extension;
    }

}
