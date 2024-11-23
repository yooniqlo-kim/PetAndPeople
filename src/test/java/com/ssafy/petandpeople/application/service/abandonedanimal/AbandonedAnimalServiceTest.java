package com.ssafy.petandpeople.application.service.abandonedanimal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ssafy.petandpeople.application.dto.abandonedanimal.AbandonedAnimalDto;
import com.ssafy.petandpeople.application.dto.abandonedanimal.ApiRequestParams;
import com.ssafy.petandpeople.infrastructure.external.DataApiClient;
import com.ssafy.petandpeople.infrastructure.external.JsonParser;
import com.ssafy.petandpeople.infrastructure.persistence.repository.RedisRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class AbandonedAnimalServiceTest {

    private static final String API_URL = "https://openapi.gg.go.kr/AbdmAnimalProtect";
    private static final String TEST_REDIS_KEY = "TEST_DATA";
    private static final int SIZE = 100;

    private final JsonParser jsonParser;
    private final RedisRepository redisRepository;
    private final DataApiClient dataApiClient;

    @Autowired
    public AbandonedAnimalServiceTest(JsonParser jsonParser, RedisRepository redisRepository, DataApiClient dataApiClient) {
        this.jsonParser = jsonParser;
        this.redisRepository = redisRepository;
        this.dataApiClient = dataApiClient;
    }

    @Test
    @DisplayName("API 응답 데이터를 Redis에 저장 및 검증 성공")
    void initialize_성공() {
        ApiRequestParams params = new ApiRequestParams(API_URL, 1, SIZE);
        String response = dataApiClient.requestApi(params);

        Object errorCode = jsonParser.extractErrorCode(response);
        assertNull(errorCode);

        String jsonData = jsonParser.extractData(response, "/AbdmAnimalProtect/1/row");
        List<AbandonedAnimalDto> abandonedAnimalsFromApi = jsonParser.jsonToDtoList(jsonData, new TypeReference<List<AbandonedAnimalDto>>() {});

        assertNotNull(abandonedAnimalsFromApi);
        assertEquals(SIZE, abandonedAnimalsFromApi.size());

        redisRepository.save(TEST_REDIS_KEY, abandonedAnimalsFromApi);

        List<AbandonedAnimalDto> abandonedAnimalsFromRedis = jsonParser.convertToType(redisRepository.find(TEST_REDIS_KEY), new TypeReference<List<AbandonedAnimalDto>>() {});

        assertNotNull(abandonedAnimalsFromRedis);
        assertEquals(SIZE, abandonedAnimalsFromRedis.size());
        assertEquals(abandonedAnimalsFromApi.size(), abandonedAnimalsFromRedis.size());

        for (int i = 0; i < SIZE; i++) {
            assertEquals(abandonedAnimalsFromApi.get(i).getAbdmIdntfyNo(), abandonedAnimalsFromRedis.get(i).getAbdmIdntfyNo());
        }
    }

}
