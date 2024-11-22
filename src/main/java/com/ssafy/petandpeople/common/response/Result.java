package com.ssafy.petandpeople.presentation.response;

public class Result {
    private static final com.ssafy.petandpeople.presentation.response.Result SUCCESS = new com.ssafy.petandpeople.presentation.response.Result("성공");

    private final String resultMessage;

    private Result(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public static com.ssafy.petandpeople.presentation.response.Result OK() {
        return SUCCESS;
    }

    public static com.ssafy.petandpeople.presentation.response.Result ERROR(String resultMessage) {
        return new com.ssafy.petandpeople.presentation.response.Result(resultMessage);
    }

    public String getResultMessage() {
        return resultMessage;
    }

}