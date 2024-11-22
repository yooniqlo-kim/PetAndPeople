package com.ssafy.petandpeople.presentation.controller;

import com.ssafy.petandpeople.application.dto.job.PartTimePostDto;
import com.ssafy.petandpeople.application.service.job.parttime.PartTimePostService;
import com.ssafy.petandpeople.presentation.response.Api;
import io.swagger.annotations.ApiParam;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/partTime")
public class PartTimeController {
    private final PartTimePostService partTimePostService;

    private PartTimeController(PartTimePostService partTimePostService) {
        this.partTimePostService = partTimePostService;
    }

    @PostMapping(value = "/create/post", consumes = "multipart/form-data")
    public Api<Object> createPartTimePost(
            HttpServletRequest request,
            @RequestPart("partTimePostDto") PartTimePostDto partTimePostDto,
            @RequestPart("thumbnail")
            @ApiParam(value = "썸네일 이미지 파일", required = true)
            MultipartFile thumbnail
    ) {
        partTimePostService.createPartTimePost(request, partTimePostDto, thumbnail);

        return Api.OK();
    }

    @PostMapping(value = "/update/post/{postKey}", consumes = "multipart/form-data")
    public Api<Object> updatePartTimePost(
            HttpServletRequest request,
            @PathVariable Long postKey,
            @RequestBody PartTimePostDto partTimePostDto,
            @ApiParam(value = "썸네일 이미지 파일", required = true)
            MultipartFile thumbnail
    ) {
        partTimePostService.updatePartTimePost(request, postKey, thumbnail, partTimePostDto);

        return Api.OK();
    }

    @GetMapping("/select/detail-post/{postKey}")
    public Api<PartTimePostDto> getDetailAboutPartTimePost(@PathVariable Long postKey) {
        PartTimePostDto result = partTimePostService.getDetailAboutPartTimePost(postKey);

        return Api.OK(result);
    }

    @GetMapping("/select/user-posts")
    public Api<List<PartTimePostDto>> findPartTimePostsCreatedByUser(HttpServletRequest request) {
        List<PartTimePostDto> results = partTimePostService.findPartTimePostsCreatedByUser(request);

        return Api.OK(results);
    }

    @GetMapping("/select/all-posts")
    public Api<List<PartTimePostDto>> findAllPartTimePosts() {
        List<PartTimePostDto> results = partTimePostService.findAllPartTimePosts();

        return Api.OK(results);
    }

    @GetMapping("/delete/post/{postKey}")
    public Api<Object> deletePartTimePost(HttpServletRequest request, @PathVariable Long postKey) {
        partTimePostService.deletePartTimePost(request, postKey);

        return Api.OK();
    }

    @GetMapping("/validate/post-ownership/{postKey}")
    public Api<Object> validatePartTimePostOwnership(HttpServletRequest request, @PathVariable Long postKey) {
        partTimePostService.validatePartTimePostOwnership(request, postKey);

        return Api.OK();
    }

}
