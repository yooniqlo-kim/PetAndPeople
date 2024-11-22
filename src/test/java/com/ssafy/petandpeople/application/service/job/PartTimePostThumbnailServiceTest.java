package com.ssafy.petandpeople.application.service.job;

import com.amazonaws.services.s3.AmazonS3;
import com.ssafy.petandpeople.application.converter.job.ThumbnailConverter;
import com.ssafy.petandpeople.application.dto.job.ThumbnailDto;
import com.ssafy.petandpeople.application.service.job.parttime.PartTimePostThumbnailService;
import com.ssafy.petandpeople.config.S3Config;
import com.ssafy.petandpeople.common.exception.job.InvalidFileFormatException;
import com.ssafy.petandpeople.common.exception.job.ThumbnailNotSavedException;
import com.ssafy.petandpeople.common.exception.job.ThumbnailNotUploadedException;
import com.ssafy.petandpeople.common.utils.UUIDGenerator;
import com.ssafy.petandpeople.infrastructure.persistence.entity.job.parttime.PartTimePostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.job.parttime.PartTimePostThumbnailEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.job.parttime.PartTimePostRepository;
import com.ssafy.petandpeople.infrastructure.persistence.repository.job.parttime.PartTimePostThumbnailRepository;
import com.ssafy.petandpeople.infrastructure.persistence.repository.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "test")
class PartTimePostThumbnailServiceTest {
    public static final String BUCKET_NAME = S3Config.getBucketName();

    @Mock
    private AmazonS3 amazonS3;

    private PartTimePostThumbnailService partTimePostThumbnailService;

    private final UserRepository userRepository;

    private final PartTimePostRepository partTimePostRepository;

    private final PartTimePostThumbnailRepository partTimePostThumbnailRepository;

    private PartTimePostEntity testPost;

    @Autowired
    public PartTimePostThumbnailServiceTest(UserRepository userRepository, PartTimePostRepository partTimePostRepository, PartTimePostThumbnailRepository partTimePostThumbnailRepository) {
        this.userRepository = userRepository;
        this.partTimePostRepository = partTimePostRepository;
        this.partTimePostThumbnailRepository = partTimePostThumbnailRepository;
    }

    @BeforeEach
    void setUp() {
        partTimePostThumbnailService = new PartTimePostThumbnailService(amazonS3, partTimePostThumbnailRepository);

        UserEntity testUser = new UserEntity(
                "test_id",
                "test_password",
                "test_name",
                "test_number",
                "test_address"
        );
        UserEntity userKey = userRepository.save(testUser);

        PartTimePostEntity testPost = new PartTimePostEntity(
                userKey,
                "test_post_tile_1",
                "test_deadline_1",
                "test_count_1",
                "test_salary_1",
                "test_age_1",
                "test_period_1",
                "test_days_1",
                "test_hours_1",
                "test_address_1",
                "test_content_1",
                "test_manager_name",
                "test_manager_phone_number"
        );
        this.testPost = partTimePostRepository.save(testPost);
    }

    @AfterEach
    void setDown() {
        partTimePostThumbnailRepository.deleteAll();
        partTimePostRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("썸네일 업로드 및 저장 성공")
    void saveThumbnail_성공() throws IOException {
        MultipartFile mockThumbnail = mock(MultipartFile.class);
        when(mockThumbnail.getContentType()).thenReturn("image/png");
        when(mockThumbnail.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));
        when(mockThumbnail.getOriginalFilename()).thenReturn("test.png");

        try (MockedStatic<UUIDGenerator> mockedUUIDGenerator = mockStatic(UUIDGenerator.class)) {
            mockedUUIDGenerator.when(UUIDGenerator::generateUUIDtoString).thenReturn("fc960da0-b66d-46e1-8e33-2d753df09bfb");

            ThumbnailDto mockThumbnailDto = ThumbnailDto.create(mockThumbnail, testPost);
            String expectedUrl = "https://s3.amazonaws.com/test-bucket/" + mockThumbnailDto.getS3FileName();

            when(amazonS3.getUrl(BUCKET_NAME, mockThumbnailDto.getS3FileName())).thenReturn(new java.net.URL(expectedUrl));

            assertTrue(partTimePostThumbnailService.saveThumbnail(mockThumbnail, testPost));

            verify(amazonS3, times(1)).putObject(any());
        }
    }

    @Test
    @DisplayName("올바르지 않은 파일 타입")
    void saveThumbnail_InvalidFileType() {
        MultipartFile mockThumbnail = mock(MultipartFile.class);
        when(mockThumbnail.getContentType()).thenReturn("application/pdf");

        InvalidFileFormatException exception = assertThrows(InvalidFileFormatException.class, () ->
                partTimePostThumbnailService.saveThumbnail(mockThumbnail, testPost));
        assertEquals("유효하지 않은 파일타입 입니다. 이미지 파일을 올려주세요.", exception.getErrorCodeIfs().getMessage());
    }

    @Test
    @DisplayName("S3에 업로드 실패")
    void saveThumbnail_S3UploadFailure() throws IOException {
        MultipartFile mockThumbnail = mock(MultipartFile.class);
        when(mockThumbnail.getContentType()).thenReturn("image/png");
        when(mockThumbnail.getInputStream()).thenThrow(new ThumbnailNotUploadedException(""));
        when(mockThumbnail.getOriginalFilename()).thenReturn("test.png");

        ThumbnailNotUploadedException exception = assertThrows(ThumbnailNotUploadedException.class, () ->
                partTimePostThumbnailService.saveThumbnail(mockThumbnail, testPost));

        assertEquals("일시적인 서버에러가 발생했습니다. 잠시 후 다시 시도해주세요.", exception.getErrorCodeIfs().getMessage());
    }

    @Test
    @DisplayName("DB에 저장 실패")
    void saveThumbnail_DBSaveFailure() throws IOException {
        MultipartFile mockThumbnail = mock(MultipartFile.class);
        when(mockThumbnail.getContentType()).thenReturn("image/png");
        when(mockThumbnail.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));
        when(mockThumbnail.getOriginalFilename()).thenReturn("test.png");

        try (MockedStatic<UUIDGenerator> mockedUUIDGenerator = mockStatic(UUIDGenerator.class)) {
            mockedUUIDGenerator.when(UUIDGenerator::generateUUIDtoString).thenReturn("fc960da0-b66d-46e1-8e33-2d753df09bfb");

            ThumbnailDto mockThumbnailDto = ThumbnailDto.create(mockThumbnail, testPost);

            String filePath = "https://s3.amazonaws.com/" + mockThumbnailDto.getS3FileName();

            when(amazonS3.getUrl(BUCKET_NAME, mockThumbnailDto.getS3FileName())).thenReturn(new java.net.URL(filePath));

            partTimePostRepository.deleteAll();

            ThumbnailNotSavedException exception = assertThrows(ThumbnailNotSavedException.class, () ->
                    partTimePostThumbnailService.saveThumbnail(mockThumbnail, testPost));

            assertEquals("일시적인 서버에러가 발생했습니다. 잠시 후 다시 시도해주세요.", exception.getErrorCodeIfs().getMessage());

            verify(amazonS3, times(1)).deleteObject(BUCKET_NAME, mockThumbnailDto.getS3FileName());
        }
    }

    @Test
    @DisplayName("썸네일 수정 성공 - 기존 썸네일 삭제 후 새 썸네일 업로드")
    void updateThumbnail_성공() throws IOException {
        MultipartFile previousThumbnail = mock(MultipartFile.class);
        when(previousThumbnail.getOriginalFilename()).thenReturn("test.png");

        try (MockedStatic<UUIDGenerator> mockedUUIDGenerator = mockStatic(UUIDGenerator.class)) {
            mockedUUIDGenerator.when(UUIDGenerator::generateUUIDtoString).thenReturn("fc960da0-b66d-46e1-8e33-2d753df09bfb");

            ThumbnailDto previousThumbnailDto = ThumbnailDto.create(previousThumbnail, testPost);

            String expectedUrl = "https://s3.amazonaws.com/test-bucket/" + previousThumbnailDto.getS3FileName();
            PartTimePostThumbnailEntity previousThumbnailEntity = ThumbnailConverter.dtoToPartTimePostThumbnailEntity(previousThumbnailDto, expectedUrl);

            partTimePostThumbnailRepository.save(previousThumbnailEntity);
        }

        MultipartFile updateThumbnail = mock(MultipartFile.class);
        when(updateThumbnail.getContentType()).thenReturn("image/jpg");
        when(updateThumbnail.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));
        when(updateThumbnail.getOriginalFilename()).thenReturn("test.jpg");

        ThumbnailDto updateThumbnailDto = ThumbnailDto.create(updateThumbnail, "fc960da0-b66d-46e1-8e33-2d753df09bfb", testPost);
        String expectedUrl = "https://s3.amazonaws.com/test-bucket/" + updateThumbnailDto.getS3FileName();

        when(amazonS3.putObject(any())).thenReturn(null);
        when(amazonS3.getUrl(BUCKET_NAME, updateThumbnailDto.getS3FileName())).thenReturn(new java.net.URL(expectedUrl));

        assertTrue(partTimePostThumbnailService.updateThumbnail(updateThumbnail, testPost));

        verify(amazonS3, times(1)).deleteObject(any(), any());
        verify(amazonS3, times(1)).putObject(any());

        PartTimePostThumbnailEntity updateThumbnailEntity = partTimePostThumbnailRepository.findByThumbnailKey("fc960da0-b66d-46e1-8e33-2d753df09bfb");

        assertEquals(updateThumbnailDto.getOriginalFileName(), updateThumbnailEntity.getOriginalFileName());
        assertEquals(updateThumbnailDto.getS3FileName(), updateThumbnailEntity.getS3FileName());
    }

}
