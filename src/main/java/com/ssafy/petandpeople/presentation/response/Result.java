package com.ssafy.petandpeople.presentation.response;

import com.ssafy.petandpeople.common.error.ErrorCodeIfs;

public class Result {
    private static final Result SUCCESS = new Result(200, "성공");

    private final Integer resultCode;
    private final String resultMessage;

    private Result(Integer resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    private Result(ErrorCodeIfs errorCodeIfs, String resultMessage) {
        this.resultCode = errorCodeIfs.getErrorCode();
        this.resultMessage = resultMessage;
    }

    public static Result OK() {
        return SUCCESS;
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs) {
        return new Result(errorCodeIfs, "실패");
    }

    public static Result ERROR(Integer errorCode) {
        return new Result(errorCode, "실패");
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

}
