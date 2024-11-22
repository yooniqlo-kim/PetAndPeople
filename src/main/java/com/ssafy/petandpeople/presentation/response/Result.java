package com.ssafy.petandpeople.presentation.response;

public class Result {
    private static final Result SUCCESS = new Result("성공");

    private final String resultMessage;

    private Result(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public static Result OK() {
        return SUCCESS;
    }

    public static Result ERROR(String resultMessage) {
        return new Result(resultMessage);
    }

    public String getResultMessage() {
        return resultMessage;
    }

}