package com.ssafy.petandpeople.application.service.ai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ssafy.petandpeople.application.converter.adoption.AdoptionConverter;
import com.ssafy.petandpeople.application.dto.adoption.AdoptionDto;
import com.ssafy.petandpeople.application.dto.adoption.AdoptionSummaryDto;
import com.ssafy.petandpeople.application.dto.ai.UserPreferenceDto;
import com.ssafy.petandpeople.infrastructure.external.JsonParser;
import com.ssafy.petandpeople.infrastructure.external.OpenAiApiClient;
import com.ssafy.petandpeople.infrastructure.persistence.repository.RedisRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiService {

    private static final Dotenv DOTENV = Dotenv.load();
    private static final String ADOPTION_REDIS_KEY = DOTENV.get("ADOPTION_REDIS_KEY");

    private final RedisRepository redisRepository;
    private final OpenAiApiClient openAiClient;
    private final JsonParser jsonParser;

    public AiService(RedisRepository redisRepository, OpenAiApiClient openAiClient, JsonParser jsonParser) {
        this.redisRepository = redisRepository;
        this.openAiClient = openAiClient;
        this.jsonParser = jsonParser;
    }

    public String getRecommendationFromAi(UserPreferenceDto userPreferenceDto) {
        String userPreference = userPreferenceDto.toFormattedString();

        Object value = redisRepository.find(ADOPTION_REDIS_KEY);
        List<AdoptionDto> adoptionData = jsonParser.convertToType(value, new TypeReference<List<AdoptionDto>>() {});

        List<AdoptionSummaryDto> filteredAdoptionData = AdoptionConverter.toFilteredData(adoptionData);

        String selectedAdoptionData = filteredAdoptionData.subList(0,95).toString();

        return openAiClient.generateResponse(userPreference, selectedAdoptionData);
    }

}
