package com.ssafy.petandpeople.application.service.job.parttime;

import com.ssafy.petandpeople.application.converter.job.PartTimeConverter;
import com.ssafy.petandpeople.application.dto.job.PartTimePostDto;
import com.ssafy.petandpeople.common.exception.job.PostNotAuthorizedException;
import com.ssafy.petandpeople.common.utils.SessionManager;
import com.ssafy.petandpeople.infrastructure.persistence.entity.job.parttime.PartTimePostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.job.parttime.PartTimePostRepository;
import com.ssafy.petandpeople.infrastructure.persistence.repository.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PartTimePostService {

    private final UserRepository userRepository;
    private final PartTimePostRepository partTimePostRepository;
    private final PartTimePostThumbnailService partTimePostThumbnailService;

    public PartTimePostService(
            UserRepository userRepository,
            PartTimePostRepository partTimePostRepository,
            PartTimePostThumbnailService partTimePostThumbnailService
    ) {
        this.userRepository = userRepository;
        this.partTimePostRepository = partTimePostRepository;
        this.partTimePostThumbnailService = partTimePostThumbnailService;
    }

    @Transactional
    public boolean createPartTimePost(HttpServletRequest request, PartTimePostDto partTimePostDto, MultipartFile thumbnail) {
        UserEntity userKey = findUserKey(request);

        PartTimePostEntity partTimePostEntity = PartTimeConverter.dtoToEntity(partTimePostDto, userKey);

        PartTimePostEntity postKey = partTimePostRepository.save(partTimePostEntity);

        partTimePostThumbnailService.saveThumbnail(thumbnail, postKey);

        return true;
    }

    @Transactional
    public Boolean updatePartTimePost(HttpServletRequest request, Long postKey, MultipartFile thumbnail, PartTimePostDto partTimePostDto) {
        validatePartTimePostOwnership(request, postKey);

        PartTimePostEntity partTimePostForUpdate = PartTimeConverter.dtoToEntity(partTimePostDto);
        PartTimePostEntity savedPartTimePost = findSavedPartTimePost(postKey);

        partTimePostForUpdate.setPostKey(savedPartTimePost.getPostKey());
        partTimePostForUpdate.setUserKey(savedPartTimePost.getUserKey());
        partTimePostForUpdate.setManagerName(savedPartTimePost.getManagerName());
        partTimePostForUpdate.setManagerPhoneNumber(savedPartTimePost.getManagerPhoneNumber());

        partTimePostThumbnailService.updateThumbnail(thumbnail, partTimePostForUpdate);

        partTimePostRepository.save(partTimePostForUpdate);

        return true;
    }

    public PartTimePostDto getDetailAboutPartTimePost(Long postKey) {
        PartTimePostEntity partTimePostEntity = partTimePostRepository.findByPostKey(postKey);

        String thumbnailPath = partTimePostThumbnailService.findThumbnailPath(partTimePostEntity);

        return PartTimeConverter.entityToDto(partTimePostEntity, thumbnailPath);
    }

    public List<PartTimePostDto> findPartTimePostsCreatedByUser(HttpServletRequest request) {
        UserEntity userKey = findUserKey(request);
        List<PartTimePostEntity> partTimePostEntities = partTimePostRepository.findAllByUserKey(userKey);

        return partTimePostEntities.stream()
                .map(partTimePostEntity -> {
                    String thumbnailPath = partTimePostThumbnailService.findThumbnailPath(partTimePostEntity);
                    return PartTimeConverter.entityToDto(partTimePostEntity, thumbnailPath);
                })
                .toList();
    }

    public List<PartTimePostDto> findAllPartTimePosts() {
        List<PartTimePostEntity> partTimePostEntities = partTimePostRepository.findAll();

        return partTimePostEntities.stream()
                .map(partTimePostEntity -> {
                    String filePath = partTimePostThumbnailService.findThumbnailPath(partTimePostEntity);
                    return PartTimeConverter.entityToDto(partTimePostEntity, filePath);
                })
                .toList();
    }

    public boolean deletePartTimePost(HttpServletRequest request, Long postKey) {
        validatePartTimePostOwnership(request, postKey);

        PartTimePostEntity partTimePostEntity = partTimePostRepository.findByPostKey(postKey);

        partTimePostThumbnailService.deleteThumbnail(partTimePostEntity);
        partTimePostRepository.deleteByPostKey(postKey);

        return true;
    }

    public void validatePartTimePostOwnership(HttpServletRequest request, Long postKey) {
        UserEntity userKey = findUserKey(request);
        partTimePostRepository.findByPostKeyAndUserKey(postKey, userKey).orElseThrow(PostNotAuthorizedException::new);
    }

    private UserEntity findUserKey(HttpServletRequest request) {
        return userRepository
                .findByUserKey(SessionManager.getUserKeyFromSession(request));
    }

    private PartTimePostEntity findSavedPartTimePost(Long postKey) {
        return partTimePostRepository.findByPostKey(postKey);
    }

}