package com.ssafy.petandpeople.presentation.controller;

import com.ssafy.petandpeople.application.dto.PartTimePostDto;
import com.ssafy.petandpeople.application.service.PartTimeService;
import com.ssafy.petandpeople.presentation.response.Api;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/partTime")
public class PartTimeController {
    private final PartTimeService partTimeService;

    private PartTimeController(PartTimeService partTimeService) {
        this.partTimeService = partTimeService;
    }

    @PostMapping("/create/post")
    public Api<Object> createPartTimePost(@RequestBody PartTimePostDto partTimePostDto, HttpServletRequest request) {
        partTimeService.createPartTimePost(partTimePostDto, request);

        return Api.OK();
    }

    @PostMapping("/update/post/{postKey}")
    public Api<Object> updatePartTimePost(@PathVariable Long postKey, @RequestBody PartTimePostDto partTimePostDto, HttpServletRequest request) {
        partTimeService.updatePartTimePost(postKey, partTimePostDto, request);

        return Api.OK();
    }

    @GetMapping("/select/detail-post/{postKey}")
    public Api<PartTimePostDto> findPartTimePostByPostKey(@PathVariable Long postKey) {
        PartTimePostDto result = partTimeService.findPartTimePostByPostKey(postKey);

        return Api.OK(result);
    }

    @GetMapping("/select/user-post")
    public Api<List<PartTimePostDto>> findPartTimePostByUserKey(HttpServletRequest request) {
        List<PartTimePostDto> results = partTimeService.findPartTimePostByUserKey(request);

        return Api.OK(results);
    }

    @GetMapping("/select/all-post")
    public Api<List<PartTimePostDto>> findAllPartTimePost() {
        List<PartTimePostDto> results = partTimeService.findAllPartTimePost();

        return Api.OK(results);
    }

        return Api.OK(allPartTimePost);
    }

}
