package com.ssafy.petandpeople.presentation.controller.adoption;

import com.ssafy.petandpeople.application.dto.adoption.AdoptionDto;
import com.ssafy.petandpeople.application.service.adoption.AdoptionService;
import com.ssafy.petandpeople.presentation.response.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adoption")
public class AdoptionController {

    private final AdoptionService adoptionService;

    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @GetMapping("/data")
    public Api<List<AdoptionDto>> getAdoptionData(
            @RequestParam(defaultValue = "1") int pageNum) {

        List<AdoptionDto> adoptionData = adoptionService.getAdoptionData(pageNum);

        return Api.OK(adoptionData);
    }

}
