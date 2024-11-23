package com.ssafy.petandpeople.presentation.controller.post;


import com.ssafy.petandpeople.application.dto.post.PostDto;
import com.ssafy.petandpeople.application.service.post.PostService;
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
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    private PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public Api<Object> createPost(
            HttpServletRequest request,
            @RequestPart("postDto") PostDto postDto,
            @RequestPart("thumbnail")
            @ApiParam(value = "썸네일 이미지 파일", required = true)
            MultipartFile thumbnail
    ) {
        postService.createPost(request, postDto, thumbnail);

        return Api.OK();
    }

    @PostMapping(value = "/update/{postKey}", consumes = "multipart/form-data")
    public Api<Object> updatePost(
            HttpServletRequest request,
            @PathVariable Long postKey,
            @RequestBody PostDto postDto,
            @ApiParam(value = "썸네일 이미지 파일", required = true)
            MultipartFile thumbnail
    ) {
        postService.updatePost(request, postKey, thumbnail, postDto);

        return Api.OK();
    }

    @GetMapping("/select/detail/{postKey}")
    public Api<PostDto> getDetailAboutPost(@PathVariable Long postKey) {
        PostDto result = postService.getDetailAboutPost(postKey);

        return Api.OK(result);
    }

    @GetMapping("/select/user")
    public Api<List<PostDto>> findPostsCreatedByUser(HttpServletRequest request) {
        List<PostDto> results = postService.findPostsCreatedByUser(request);

        return Api.OK(results);
    }

    @GetMapping("/select/all")
    public Api<List<PostDto>> findAllPosts() {
        List<PostDto> results = postService.findAllPosts();

        return Api.OK(results);
    }

    @GetMapping("/delete/{postKey}")
    public Api<Object> deletePost(HttpServletRequest request, @PathVariable Long postKey) {
        postService.deletePost(request, postKey);

        return Api.OK();
    }

    @GetMapping("/validate/ownership/{postKey}")
    public Api<Object> validatePostOwnership(HttpServletRequest request, @PathVariable Long postKey) {
        postService.validatePostOwnership(request, postKey);

        return Api.OK();
    }

}