package com.ssafy.petandpeople.infrastructure.external;

import com.ssafy.petandpeople.application.dto.abandonedanimal.ApiRequestParams;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class DataApiClient {

    private final RestTemplate restTemplate;

    public DataApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String requestApi(ApiRequestParams params) {
        URI uri = UriComponentsBuilder.fromHttpUrl(params.getApiUrl())
                .queryParam("KEY", params.getApiKey())
                .queryParam("Type", params.getReturnType())
                .queryParam("pIndex", params.getPageIndex())
                .queryParam("pSize", params.getPageSize())
                .queryParam("STATE_NM", params.getStateNm())
                .encode()
                .build()
                .toUri();

        return restTemplate.getForObject(uri, String.class);
    }

}
