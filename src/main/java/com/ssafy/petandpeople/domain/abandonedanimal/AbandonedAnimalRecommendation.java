package com.ssafy.petandpeople.domain.abandonedanimal;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AbandonedAnimalRecommendation {

    private static final String DETAIL_INSTRUCTION =
            """
                    Generate a response in Korean based on the following requirements:
                    Recommend the top 3 most suitable abandoned animals for the user based on their preferences.
                    Provide the identification number of each recommended animal.
                    Explain why each animal is the most suitable choice for the user.
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
                               
                    Repeat the above format for the top 3 recommendations, each with the most suitable animal for the user. Ensure all keys, labels, and values are in Korean, strictly adhering to the above format. Avoid using any English terms or labels in the response. Exclude any final concluding sentence.
            """;

    public Map<String, Object> createRecommendationPrompt(String userPreference, String abandonedAnimals) {
        return Map.of(
                "model", "gpt-4o",
                "messages", List.of(
                        Map.of("role", "system", "content", "You are an AI assistant that recommends abandoned animals to users based on their preferences."),
                        Map.of("role", "user", "content", "User preferences: " + userPreference),
                        Map.of("role", "user", "content", "AbandonedAnimals data: " + abandonedAnimals),
                        Map.of("role", "user", "content", DETAIL_INSTRUCTION)
                ),
                "max_tokens", 3000,
                "temperature", 1.0
        );
    }

}
