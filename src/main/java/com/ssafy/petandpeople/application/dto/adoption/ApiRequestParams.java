package com.ssafy.petandpeople.application.dto.adoption;

import io.github.cdimascio.dotenv.Dotenv;

public class ApiRequestParams {

    private static final Dotenv dotenv = Dotenv.load();

    private final String apiUrl;
    private final String apiKey;
    private final String returnType;
    private final Integer pageIndex;
    private final Integer pageSize;
    private final String stateNm;

    public ApiRequestParams(String apiUrl, int pageIndex, int pageSize) {
        this.apiUrl = apiUrl;
        this.apiKey = dotenv.get("ADOPTION_API_KEY");
        this.returnType = "json";
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.stateNm = "보호중";
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getReturnType() {
        return returnType;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String getStateNm() {
        return stateNm;
    }

}
