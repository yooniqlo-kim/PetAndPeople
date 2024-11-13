package com.ssafy.petandpeople.presentation.controller;

import com.ssafy.petandpeople.application.dto.PartTimeDto;
import com.ssafy.petandpeople.application.service.PartTimeService;
import com.ssafy.petandpeople.presentation.response.Api;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partTime")
public class PartTimeController {
    private final PartTimeService partTimeService;

    private PartTimeController(PartTimeService partTimeService) {
        this.partTimeService = partTimeService;
    }

    @PostMapping("/create/post")
    public Api<Object> createPartTimePost(@RequestBody PartTimeDto partTimeDto, HttpServletRequest request) throws Exception {
        partTimeService.createPartTimePost(partTimeDto, request);

        return Api.OK();
    }

}
