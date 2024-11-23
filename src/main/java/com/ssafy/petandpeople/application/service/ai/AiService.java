package com.ssafy.petandpeople.application.service.ai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ssafy.petandpeople.application.converter.adoption.AdoptionConverter;
import com.ssafy.petandpeople.application.dto.abandonedanimal.AbandonedAnimalDto;
import com.ssafy.petandpeople.application.dto.ai.FilteredAbandonedAnimalDto;
import com.ssafy.petandpeople.application.dto.ai.UserPreferenceDto;
import com.ssafy.petandpeople.domain.abandonedanimal.AbandonedAnimalRecommendation;
import com.ssafy.petandpeople.infrastructure.external.JsonParser;
import com.ssafy.petandpeople.infrastructure.external.OpenAiApiClient;
import com.ssafy.petandpeople.infrastructure.persistence.repository.RedisRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AiService {

    private static final Dotenv DOTENV = Dotenv.load();
    private static final String ABANDONED_ANIMAL_REDIS_KEY = DOTENV.get("ABANDONED_ANIMAL_REDIS_KEY");

    private final AbandonedAnimalRecommendation abandonedAnimalRecommendation;
    private final JsonParser jsonParser;
    private final OpenAiApiClient openAiClient;
    private final RedisRepository redisRepository;

    public AiService(AbandonedAnimalRecommendation abandonedAnimalRecommendation, JsonParser jsonParser, OpenAiApiClient openAiClient, RedisRepository redisRepository) {
        this.abandonedAnimalRecommendation = abandonedAnimalRecommendation;
        this.jsonParser = jsonParser;
        this.openAiClient = openAiClient;
        this.redisRepository = redisRepository;
    }

    public String recommendAbandonedAnimals(UserPreferenceDto userPreferenceDto) {
        String userPreference = userPreferenceDto.toFormattedStringForPrompting();

        Object value = redisRepository.find(ABANDONED_ANIMAL_REDIS_KEY);
        List<AbandonedAnimalDto> abandonedAnimals = jsonParser.convertToType(value, new TypeReference<List<AbandonedAnimalDto>>() {});

        List<FilteredAbandonedAnimalDto> filteredAbandonedAnimals = AdoptionConverter.toFilteredDataCluster(abandonedAnimals);

        String abandonedAnimalsOrderByAscendingDate = filteredAbandonedAnimals.subList(0,95).toString();

        Map<String, Object> prompt = abandonedAnimalRecommendation.createRecommendationPrompt(userPreference, abandonedAnimalsOrderByAscendingDate);

        return openAiClient.generateResponse(prompt);
    }

}
