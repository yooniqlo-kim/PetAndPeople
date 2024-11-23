package com.ssafy.petandpeople.presentation.controller.abandonedanimal;

import com.ssafy.petandpeople.application.dto.abandonedanimal.AbandonedAnimalDto;
import com.ssafy.petandpeople.application.service.abandonedanimal.AbandonedAnimalService;
import com.ssafy.petandpeople.presentation.response.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adoption")
public class AbandonedAnimalController {

    private final AbandonedAnimalService abandonedAnimalService;

    public AbandonedAnimalController(AbandonedAnimalService abandonedAnimalService) {
        this.abandonedAnimalService = abandonedAnimalService;
    }

    @GetMapping("/data")
    public Api<List<AbandonedAnimalDto>> getAdoptionData(
            @RequestParam(defaultValue = "1") int pageNum) {

        List<AbandonedAnimalDto> adoptionData = abandonedAnimalService.getAbandonedAnimals(pageNum);

        return Api.OK(adoptionData);
    }

}
