package com.ssafy.petandpeople.presentation.controller.ai;

import com.ssafy.petandpeople.application.dto.ai.UserPreferenceDto;
import com.ssafy.petandpeople.application.service.ai.AiService;
import com.ssafy.petandpeople.presentation.response.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/recommend")
    public Api<Object> recommendBestPet(@RequestBody UserPreferenceDto userPreferenceDto) {
        String recommendation = aiService.getRecommendationFromAi(userPreferenceDto);

        return Api.OK(recommendation);
    }

}
