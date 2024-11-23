package com.ssafy.petandpeople.application.service.abandonedanimal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ssafy.petandpeople.application.dto.abandonedanimal.AbandonedAnimalDto;
import com.ssafy.petandpeople.application.dto.abandonedanimal.ApiRequestParams;
import com.ssafy.petandpeople.application.dto.abandonedanimal.ErrorResponseDto;
import com.ssafy.petandpeople.common.exception.abandonedanimal.ErrorResponseException;
import com.ssafy.petandpeople.infrastructure.external.DataApiClient;
import com.ssafy.petandpeople.infrastructure.external.JsonParser;
import com.ssafy.petandpeople.infrastructure.persistence.repository.RedisRepository;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AbandonedAnimalService {

    private static final int PAGE_SIZE = 15;
    private static final Dotenv DOTENV = Dotenv.load();
    private static final String API_URL = "https://openapi.gg.go.kr/AbdmAnimalProtect";
    private static final String ABANDONED_ANIMAL_REDIS_KEY = DOTENV.get("ABANDONED_ANIMAL_REDIS_KEY");

    private final JsonParser jsonParser;
    private final DataApiClient dataApiClient;
    private final RedisRepository redisRepository;

    public AbandonedAnimalService(JsonParser jsonParser, DataApiClient dataApiClient, RedisRepository redisRepository) {
        this.jsonParser = jsonParser;
        this.dataApiClient = dataApiClient;
        this.redisRepository = redisRepository;
    }

    @PostConstruct
    public void initialize() {
        List<AbandonedAnimalDto> abandonedAnimals = new ArrayList<>();

        for (int pageIndex = 1; pageIndex <= 2; pageIndex++) {
            ApiRequestParams params = new ApiRequestParams(API_URL, pageIndex, 1000);
            String response = dataApiClient.requestApi(params);

            validateResponse(response);

            String jsonData = jsonParser.extractData(response, "/AbdmAnimalProtect/1/row");

            List<AbandonedAnimalDto> adoptionData = jsonParser.jsonToDtoList(jsonData, new TypeReference<List<AbandonedAnimalDto>>() {});

            abandonedAnimals.addAll(adoptionData);
        }

        abandonedAnimals.sort(Comparator.comparing(AbandonedAnimalDto::getPblancBeginDe));

        redisRepository.save(ABANDONED_ANIMAL_REDIS_KEY, abandonedAnimals);
    }

    public List<AbandonedAnimalDto> getAbandonedAnimals(int pageNum) {
        Object value = redisRepository.find(ABANDONED_ANIMAL_REDIS_KEY);

        List<AbandonedAnimalDto> abandonedAnimals = jsonParser.convertToType(value, new TypeReference<List<AbandonedAnimalDto>>() {});

        return getPageSlice(abandonedAnimals, pageNum);
    }

    private void validateResponse(String response) {
        ErrorResponseDto errorResponse = jsonParser.extractErrorCode(response);

        if (errorResponse != null) {
            throw new ErrorResponseException("<API 응답 에러 발생> " + "CODE:" + errorResponse.getCode() + " MESSAGE: " + errorResponse.getMessage());
        }
    }

    private <T> List<T> getPageSlice(List<T> abandonedAnimals, int pageNum) {
        int start = Math.max(0, (pageNum - 1) * PAGE_SIZE);
        int end = Math.min(start + PAGE_SIZE, abandonedAnimals.size());

        return abandonedAnimals.subList(start, end);
    }

}
