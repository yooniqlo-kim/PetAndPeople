package com.ssafy.petandpeople.application.dto.adoption;

public class ErrorResponseDto {

    private final String code;
    private final String message;

    public ErrorResponseDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
