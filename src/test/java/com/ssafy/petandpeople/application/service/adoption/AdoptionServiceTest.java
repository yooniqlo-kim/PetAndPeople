package com.ssafy.petandpeople.application.service.adoption;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ssafy.petandpeople.application.dto.adoption.AdoptionDto;
import com.ssafy.petandpeople.application.dto.adoption.ApiRequestParams;
import com.ssafy.petandpeople.infrastructure.external.JsonParser;
import com.ssafy.petandpeople.infrastructure.external.ExternalApiClient;
import com.ssafy.petandpeople.infrastructure.persistence.repository.RedisRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdoptionServiceTest {

    private static final String API_URL = "https://openapi.gg.go.kr/AbdmAnimalProtect";
    private static final String REDIS_KEY = "TEST_DATA";
    private static final int SIZE = 100;

    private final JsonParser jsonParser;
    private final RedisRepository redisRepository;
    private final ExternalApiClient externalApiClient;

    @Autowired
    public AdoptionServiceTest(JsonParser jsonParser, RedisRepository redisRepository, ExternalApiClient externalApiClient) {
        this.jsonParser = jsonParser;
        this.redisRepository = redisRepository;
        this.externalApiClient = externalApiClient;
    }

    @Test
    @DisplayName("API 응답 데이터를 Redis에 저장 및 검증 성공")
    void initialize_성공() {
        ApiRequestParams params = new ApiRequestParams(API_URL, 1, SIZE);
        String response = externalApiClient.requestApi(params);

        Object errorCode = jsonParser.extractErrorCode(response);
        assertNull(errorCode);

        String jsonData = jsonParser.extractData(response, "/AbdmAnimalProtect/1/row");
        List<AdoptionDto> adoptionData = jsonParser.jsonToDtoList(jsonData, new TypeReference<List<AdoptionDto>>() {
        });

        assertNotNull(adoptionData);
        assertEquals(SIZE, adoptionData.size());

        redisRepository.save(REDIS_KEY, adoptionData);

        List<AdoptionDto> dataFromRedis = jsonParser.convertToType(redisRepository.find(REDIS_KEY), new TypeReference<List<AdoptionDto>>() {
        });

        assertNotNull(dataFromRedis);
        assertEquals(SIZE, dataFromRedis.size());
        assertEquals(adoptionData.size(), dataFromRedis.size());

        for(int i=0; i<SIZE; i++) {
            assertEquals(adoptionData.get(i).getAbdmIdntfyNo(), dataFromRedis.get(i).getAbdmIdntfyNo());
        }
    }

}
