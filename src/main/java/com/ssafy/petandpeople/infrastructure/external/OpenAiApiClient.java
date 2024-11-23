package com.ssafy.petandpeople.infrastructure.external;

import com.ssafy.petandpeople.common.exception.ai.AbandonedAnimalRequestFailedException;
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

    private final JsonParser jsonParser;
    private final RestTemplate restTemplate;

    public OpenAiApiClient(JsonParser jsonParser, RestTemplate restTemplate) {
        this.jsonParser = jsonParser;
        this.restTemplate = restTemplate;
    }

    public String generateResponse(Map<String, Object> requestBody) {
        HttpHeaders headers = createHeaders();
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

    private Map<String, Object> sendRequest(HttpEntity<Map<String, Object>> requestEntity) {
        try {
            return restTemplate.postForObject(OPEN_AI_URL, requestEntity, Map.class);
        } catch (Exception e) {
            throw new AbandonedAnimalRequestFailedException(e.getMessage());
        }
    }

}
