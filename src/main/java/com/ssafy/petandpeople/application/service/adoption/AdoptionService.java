package com.ssafy.petandpeople.application.service.adoption;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ssafy.petandpeople.application.dto.adoption.ApiRequestParams;
import com.ssafy.petandpeople.application.dto.adoption.AdoptionDto;
import com.ssafy.petandpeople.application.dto.adoption.ErrorResponseDto;
import com.ssafy.petandpeople.common.exception.api.ErrorResponseException;
import com.ssafy.petandpeople.common.utils.JsonParser;
import com.ssafy.petandpeople.infrastructure.external.ExternalApiClient;
import com.ssafy.petandpeople.infrastructure.persistence.repository.RedisRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdoptionService {

    private static final String API_URL = "https://openapi.gg.go.kr/AbdmAnimalProtect";
    private static final String REDIS_KEY = "ADOPTION_DATA";

    private final JsonParser jsonParser;
    private final RedisRepository redisRepository;
    private final ExternalApiClient externalApiClient;

    public AdoptionService(JsonParser jsonParser, RedisRepository redisRepository, ExternalApiClient externalApiClient) {
        this.jsonParser = jsonParser;
        this.redisRepository = redisRepository;
        this.externalApiClient = externalApiClient;
    }

    @PostConstruct
    public void initialize() {
        List<AdoptionDto> adoptionDataAll = new ArrayList<>();

        for (int pageIndex = 1; pageIndex <= 2; pageIndex++) {
            ApiRequestParams params = new ApiRequestParams(API_URL, pageIndex, 1000);
            String response = externalApiClient.requestApi(params);

            validateResponse(response);

            String jsonData = jsonParser.extractData(response, "/AbdmAnimalProtect/1/row");

            List<AdoptionDto> adoptionData = jsonParser.mapToDtoList(jsonData, new TypeReference<List<AdoptionDto>>() {
            });

            adoptionDataAll.addAll(adoptionData);
        }

        redisRepository.save(REDIS_KEY, adoptionDataAll);
    }

    public List<AdoptionDto> getAdoptionData(int pageNum, int pageSize) {
        Object value = redisRepository.find(REDIS_KEY);

        List<AdoptionDto> allData = jsonParser.convertToType(value, new TypeReference<List<AdoptionDto>>() {
        });

        return getPageSlice(allData, pageNum, pageSize);
    }

    private void validateResponse(String response) {
        ErrorResponseDto error = jsonParser.extractErrorCode(response);

        if(hasError(error)) {
            throw new ErrorResponseException("<API 응답 에러 발생> " + "CODE:" + error.getCode() + " MESSAGE: " + error.getMessage());
        }
    }

    private Boolean hasError(ErrorResponseDto error) {
        return error != null;
    }

    private <T> List<T> getPageSlice(List<T> data, int pageNum, int pageSize) {
        int start = Math.max(0, (pageNum - 1) * pageSize);
        int end = Math.min(start + pageSize, data.size());

        return data.subList(start, end);
    }

}
