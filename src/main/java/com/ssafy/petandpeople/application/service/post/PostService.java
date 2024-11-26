package com.ssafy.petandpeople.application.service.post;

import com.ssafy.petandpeople.application.converter.post.PostConverter;
import com.ssafy.petandpeople.application.dto.post.PostDto;
import com.ssafy.petandpeople.common.exception.post.PostNotAuthorizedException;
import com.ssafy.petandpeople.infrastructure.persistence.entity.post.PostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.post.PostRepository;
import com.ssafy.petandpeople.infrastructure.persistence.repository.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PostService {
    private static final int PAGE_SIZE = 8;

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostThumbnailService postThumbnailService;

    public PostService(
            UserRepository userRepository,
            PostRepository postRepository,
            PostThumbnailService postThumbnailService
    ) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postThumbnailService = postThumbnailService;
    }

    @Transactional
    public boolean createPost(HttpServletRequest request, PostDto postDto, MultipartFile thumbnail) {
        UserEntity userKey = findUserKey(request);

        PostEntity postEntity = PostConverter.dtoToEntity(postDto, userKey);

        PostEntity postKey = postRepository.save(postEntity);
        postThumbnailService.saveThumbnail(thumbnail, postKey);

        return true;
    }

    @Transactional
    public Boolean updatePost(HttpServletRequest request, Long postKey, MultipartFile thumbnail, PostDto postDto) {
        validatePostOwnership(request, postKey);

        PostEntity updatePost = PostConverter.dtoToEntity(postDto);
        PostEntity savedPost = postRepository.findByPostKey(postKey);

        updatePost.setPostKey(savedPost.getPostKey());
        updatePost.setUserKey(savedPost.getUserKey());

        postRepository.save(updatePost);
        postThumbnailService.updateThumbnail(thumbnail, updatePost);

        return true;
    }

    public PostDto getDetailAboutPost(Long postKey) {
        PostEntity postEntity = postRepository.findByPostKey(postKey);

        String thumbnailPath = postThumbnailService.findThumbnailPath(postEntity);

        return PostConverter.entityToDto(postEntity, thumbnailPath);
    }

    public List<PostDto> findPostsCreatedByUser(HttpServletRequest request) {
        UserEntity userKey = findUserKey(request);
        List<PostEntity> postEntities = postRepository.findAllByUserKey(userKey);

        return postEntities.stream()
                .map(postEntity -> {
                    String thumbnailPath = postThumbnailService.findThumbnailPath(postEntity);
                    return PostConverter.entityToDto(postEntity, thumbnailPath);
                })
                .toList();
    }

    public List<PostDto> findAllPosts(@RequestParam(defaultValue = "1") int pageNum) {
        List<PostEntity> postEntities = postRepository.findAll();

        List<PostDto> postDtos = postEntities.stream()
                .map(postEntity -> {
                    String filePath = postThumbnailService.findThumbnailPath(postEntity);
                    return PostConverter.entityToDto(postEntity, filePath);
                })
                .toList();

        return getPageSlice(postDtos, pageNum);
    }

    private List<PostDto> getPageSlice(List<PostDto> postDtos, int pageNum) {
        int start = Math.max(0, (pageNum - 1) * PAGE_SIZE);
        int end = Math.min(start + PAGE_SIZE, postDtos.size());
        return postDtos.subList(start, end);
    }

    public boolean deletePost(HttpServletRequest request, Long postKey) {
        validatePostOwnership(request, postKey);

        PostEntity postEntity = postRepository.findByPostKey(postKey);

        postThumbnailService.deleteThumbnail(postEntity);
        postRepository.deleteByPostKey(postKey);

        return true;
    }

    public void validatePostOwnership(HttpServletRequest request, Long postKey) {
        UserEntity userKey = findUserKey(request);
        postRepository.findByPostKeyAndUserKey(postKey, userKey).orElseThrow(PostNotAuthorizedException::new);
    }

    private UserEntity findUserKey(HttpServletRequest request) {
        return userRepository
                .findByUserKey(getUserKeyFromSession(request));
    }

    private PostEntity findSavedPost(Long postKey) {
        return postRepository.findByPostKey(postKey);
    }

    private Long getUserKeyFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        validateSession(session);

        return (long) session.getAttribute("USER_KEY");
    }

    private void validateSession(HttpSession session) {
        // 세션 체크 로직
    }

}