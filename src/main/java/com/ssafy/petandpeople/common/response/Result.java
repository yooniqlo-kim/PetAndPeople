package com.ssafy.petandpeople.common.response;

import com.ssafy.petandpeople.common.error.ErrorCode;
import com.ssafy.petandpeople.common.error.ErrorCodeIfs;

public class Result {

    private final Integer resultCode;
    private final String resultMessage;

    private Result(ErrorCodeIfs errorCodeIfs, String resultMessage) {
        this.resultCode = errorCodeIfs.getErrorCode();
        this.resultMessage = resultMessage;
    }

    public static Result OK() {
        return new Result(ErrorCode.OK, "성공");
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs) {
        return new Result(errorCodeIfs, "실패");
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

}
