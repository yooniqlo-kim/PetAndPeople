package com.ssafy.petandpeople.infrastructure.external;

import com.ssafy.petandpeople.common.exception.ai.AdoptionRequestFailedException;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Map;

@Component
public class OpenAiApiClient {

    private static final String OPEN_AI_URL = "https://api.openai.com/v1/chat/completions";
    private static final Dotenv DOTENV = Dotenv.load();
    private static final String OPENAI_API_KEY = DOTENV.get("OPENAI_API_KEY");

    private final RestTemplate restTemplate;
    private final JsonParser jsonParser;

    public OpenAiApiClient(RestTemplate restTemplate, JsonParser jsonParser) {
        this.restTemplate = restTemplate;
        this.jsonParser = jsonParser;
    }

    public String generateResponse(String userPreference, String adoptionData) {
        HttpHeaders headers = createHeaders();
        Map<String, Object> requestBody = createRequestBody(userPreference, adoptionData);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        Map<String, Object> response = sendRequest(requestEntity);

        return jsonParser.parseOpenAiResponse(response);
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(OPENAI_API_KEY);

        return headers;
    }

    private Map<String, Object> createRequestBody(String userPreference, String adoptionData) {
        return Map.of(
                "model", "gpt-4-turbo",
                "messages", List.of(
                        Map.of("role", "system", "content",
                                "You are an AI assistant that recommends abandoned animals to users based on their preferences."),
                        Map.of("role", "user", "content", "User preferences: " + userPreference),
                        Map.of("role", "user", "content", "Adoption data: " + adoptionData),
                        Map.of("role", "user", "content", createRecommendationPrompt())
                ),
                "max_tokens", 800,
                "temperature", 1.0
        );
    }

    private String createRecommendationPrompt() {
        return """
                Generate a response in Korean based on the following requirements:
                Recommend the most suitable abandoned animal for the user based on their preferences.
                Provide the identification number of the recommended animal.
                Explain why this animal is the most suitable choice for the user.
                Skip any greetings in the response.
                Ensure the entire response is written in Korean.

                Add the following to the prompt:
                The response format should include:

                1. 사진 정보:  
                   [사진 URL]

                2. 추천 동물 정보:  
                   - 식별 번호: [Identification Number]
                   - 종: [Species]
                   - 색상: [Color]
                   - 나이: [Age]
                   - 체중: [Weight]
                   - 성별: [Gender]
                   - 중성화 여부: [Neutered]
                   - 특징: [Features]

                3. 보호소 정보:  
                   - 보호소 이름: [Shelter Name]
                   - 보호소 전화번호: [Shelter Phone]
                   - 보호소 위치: [Shelter Location]

                4. 추천 사유:  
                [The reason for the recommendation.]

                Ensure all keys, labels, and values are in Korean, strictly adhering to the above format. Avoid using any English terms or labels in the response. Exclude any final concluding sentence.
                """;
    }

    private Map<String, Object> sendRequest(HttpEntity<Map<String, Object>> requestEntity) {
        try {
            return restTemplate.postForObject(OPEN_AI_URL, requestEntity, Map.class);
        } catch (Exception e) {
            throw new AdoptionRequestFailedException(e.getMessage());
        }
    }

}
