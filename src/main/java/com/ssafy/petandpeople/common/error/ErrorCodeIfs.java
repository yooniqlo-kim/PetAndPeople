package com.ssafy.petandpeople.common.error;

public interface ErrorCodeIfs {

    Integer getHttpStatusCode();

    Integer getErrorCode();

    String getMessage();

}