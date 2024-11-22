package com.ssafy.petandpeople.application.service.job;

import com.amazonaws.services.s3.AmazonS3;
import com.ssafy.petandpeople.application.converter.job.PartTimeConverter;
import com.ssafy.petandpeople.application.dto.job.PartTimePostDto;
import com.ssafy.petandpeople.application.dto.job.ThumbnailDto;
import com.ssafy.petandpeople.common.exception.job.PostNotAuthorizedException;
import com.ssafy.petandpeople.common.exception.job.PostNotExistException;
import com.ssafy.petandpeople.common.utils.UUIDGenerator;
import com.ssafy.petandpeople.infrastructure.persistence.entity.job.parttime.PartTimePostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.user.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.job.parttime.PartTimePostRepository;
import com.ssafy.petandpeople.infrastructure.persistence.repository.job.parttime.PartTimePostThumbnailRepository;
import com.ssafy.petandpeople.infrastructure.persistence.repository.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static com.ssafy.petandpeople.application.service.job.PartTimePostThumbnailServiceTest.BUCKET_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "test")
public class PartTimePostServiceTest {
    private static final MockHttpServletRequest REQUEST = new MockHttpServletRequest();

    @MockBean
    private AmazonS3 amazonS3;

    private PartTimePostService partTimePostService;

    private final UserRepository userRepository;
    private final PartTimePostRepository partTimePostRepository;
    private final PartTimePostThumbnailRepository partTimePostThumbnailRepository;
    private final PartTimePostThumbnailService partTimePostThumbnailService;

    private PartTimePostEntity testPost;
    private static Long userAutoIncrementCount = 0L;
    private static Long postAutoIncrementCount = 0L;

    @Autowired
    public PartTimePostServiceTest(PartTimePostRepository partTimePostRepository, UserRepository userRepository, PartTimePostThumbnailRepository partTimePostThumbnailRepository, PartTimePostThumbnailService partTimePostThumbnailService) {
        this.userRepository = userRepository;
        this.partTimePostRepository = partTimePostRepository;
        this.partTimePostThumbnailRepository = partTimePostThumbnailRepository;
        this.partTimePostThumbnailService = partTimePostThumbnailService;
    }

    @BeforeEach
    void setUp() throws IOException {
        partTimePostService = new PartTimePostService(userRepository, partTimePostRepository, partTimePostThumbnailService);

        UserEntity testUser = new UserEntity(
                "test_id",
                "test_password",
                "test_name",
                "test_number",
                "test_address"
        );
        UserEntity userKey = userRepository.save(testUser);
        userAutoIncrementCount = userKey.getUserKey();

        testPost = new PartTimePostEntity(
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

        postAutoIncrementCount = testPost.getPostKey();

        HttpSession session = REQUEST.getSession(true);
        assert session != null;
        session.setAttribute("userKey", userAutoIncrementCount);

        MultipartFile mockThumbnail = mock(MultipartFile.class);
        when(mockThumbnail.getContentType()).thenReturn("image/png");
        when(mockThumbnail.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));
        when(mockThumbnail.getOriginalFilename()).thenReturn("test.png");

        try (MockedStatic<UUIDGenerator> mockedUUIDGenerator = mockStatic(UUIDGenerator.class)) {
            mockedUUIDGenerator.when(UUIDGenerator::generateUUIDtoString).thenReturn("fc960da0-b66d-46e1-8e33-2d753df09bfb");

            ThumbnailDto previousThumbnailDto = ThumbnailDto.create(mockThumbnail, testPost);

            String expectedUrl = "https://s3.amazonaws.com/test-bucket/" + previousThumbnailDto.getS3FileName();
            when(amazonS3.getUrl(eq(BUCKET_NAME), eq(previousThumbnailDto.getS3FileName()))).thenReturn(new java.net.URL(expectedUrl));

            partTimePostThumbnailService.saveThumbnail(mockThumbnail, testPost);
        }
    }

    @AfterEach
    void setDown() {
        partTimePostThumbnailRepository.deleteAll();
        partTimePostRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("아르바이트 게시물 생성 성공")
    void createPartTimePost_성공() throws IOException {
        PartTimePostDto createPartTimePostDto = new PartTimePostDto(
                "test_post_tile_2",
                "test_deadline_2",
                "test_count_2",
                "test_salary_2",
                "test_age_2",
                "test_period_2",
                "test_days_2",
                "test_hours_2",
                "test_address_2",
                "test_content_2",
                "test_manager_name_2",
                "test_manager_phone_number_2",
                null
        );

        PartTimePostEntity postKey = PartTimeConverter.dtoToEntity(createPartTimePostDto);

        MultipartFile mockThumbnail = mock(MultipartFile.class);
        when(mockThumbnail.getContentType()).thenReturn("image/jpg");
        when(mockThumbnail.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));
        when(mockThumbnail.getOriginalFilename()).thenReturn("test.jpg");

        try (MockedStatic<UUIDGenerator> mockedUUIDGenerator = mockStatic(UUIDGenerator.class)) {
            mockedUUIDGenerator.when(UUIDGenerator::generateUUIDtoString).thenReturn("fc960da0-b66d-46e1-8e33-2d753df09bfb");

            ThumbnailDto mockThumbnailDto = ThumbnailDto.create(mockThumbnail, postKey);
            String expectedUrl = "https://s3.amazonaws.com/test-bucket/" + mockThumbnailDto.getS3FileName();

            when(amazonS3.getUrl(eq(BUCKET_NAME), eq(mockThumbnailDto.getS3FileName()))).thenReturn(new java.net.URL(expectedUrl));

            assertTrue(partTimePostService.createPartTimePost(REQUEST, createPartTimePostDto, mockThumbnail));

            verify(amazonS3, times(2)).putObject(any());
        }
    }

    @Test
    @Transactional
    @DisplayName("아르바이트 게시물 수정 성공")
    void updatePartTimePost_성공() throws IOException {
        MultipartFile updateThumbnail = mock(MultipartFile.class);
        when(updateThumbnail.getContentType()).thenReturn("image/jpg");
        when(updateThumbnail.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));
        when(updateThumbnail.getOriginalFilename()).thenReturn("test.jpg");

        ThumbnailDto updateThumbnailDto = ThumbnailDto.create(updateThumbnail, "fc960da0-b66d-46e1-8e33-2d753df09bfb", testPost);

        String expectedUrl = "https://s3.amazonaws.com/test-bucket/" + updateThumbnailDto.getS3FileName();
        when(amazonS3.getUrl(BUCKET_NAME, updateThumbnailDto.getS3FileName())).thenReturn(new java.net.URL(expectedUrl));

        PartTimePostDto updatePartTimePostDto = new PartTimePostDto(
                "test_post_tile_3",
                "test_deadline_3",
                "test_count_3",
                "test_salary_3",
                "test_age_3",
                "test_period_3",
                "test_days_3",
                "test_hours_3",
                "test_address_3",
                "test_content_3",
                "test_manager_name_3",
                "test_manager_phone_number_3",
                null
        );

        assertTrue(partTimePostService.updatePartTimePost(REQUEST, postAutoIncrementCount, updateThumbnail, updatePartTimePostDto));

        verify(amazonS3, times(1)).deleteObject(eq(BUCKET_NAME), eq("fc960da0-b66d-46e1-8e33-2d753df09bfb_test.png"));
        verify(amazonS3, times(2)).putObject(any());

        PartTimePostEntity updatedPartTimePostEntity = partTimePostRepository.findById(postAutoIncrementCount).orElseThrow(PostNotExistException::new);

        assertEquals("test_post_tile_3", updatedPartTimePostEntity.getPostTitle());
        assertEquals("test_deadline_3", updatedPartTimePostEntity.getPartTimeDeadline());
        assertEquals("test_count_3", updatedPartTimePostEntity.getPartTimeCount());
        assertEquals("test_salary_3", updatedPartTimePostEntity.getPartTimeSalary());
    }

    @Test
    @Transactional
    @DisplayName("아르바이트 게시물 상세 정보 조회 성공")
    void findPartTimePostByPostKey_성공() {
        PartTimePostDto result = partTimePostService.getDetailAboutPartTimePost(postAutoIncrementCount);

        assertEquals("test_post_tile_1", result.getPostTitle());
        assertEquals("test_deadline_1", result.getPartTimeDeadline());
        assertEquals("test_count_1", result.getPartTimeCount());
        assertEquals("test_salary_1", result.getPartTimeSalary());
        assertEquals("https://s3.amazonaws.com/test-bucket/fc960da0-b66d-46e1-8e33-2d753df09bfb_test.png", result.getThumbnailPath());
    }

    @Test
    @Transactional
    @DisplayName("사용자가 작성한 아르바이트 게시물 조회 성공")
    void findPartTimePostsByUserKey_성공() {
        List<PartTimePostDto> results = partTimePostService.findPartTimePostsCreatedByUser(REQUEST);

        assertEquals(1, results.size());

        PartTimePostDto result = results.get(0);
        assertEquals("test_post_tile_1", result.getPostTitle());
        assertEquals("test_deadline_1", result.getPartTimeDeadline());
        assertEquals("test_count_1", result.getPartTimeCount());
        assertEquals("test_salary_1", result.getPartTimeSalary());
        assertEquals("https://s3.amazonaws.com/test-bucket/fc960da0-b66d-46e1-8e33-2d753df09bfb_test.png", result.getThumbnailPath());
    }

    @Test
    @Transactional
    @DisplayName("모든 아르바이트 게시물 조회 성공")
    void findAllPartTimePostsByUserKey_성공() {
        List<PartTimePostDto> results = partTimePostService.findAllPartTimePosts();

        assertEquals(1, results.size());

        PartTimePostDto result = results.get(0);
        assertEquals("test_post_tile_1", result.getPostTitle());
        assertEquals("test_deadline_1", result.getPartTimeDeadline());
        assertEquals("test_count_1", result.getPartTimeCount());
        assertEquals("test_salary_1", result.getPartTimeSalary());
        assertEquals("https://s3.amazonaws.com/test-bucket/fc960da0-b66d-46e1-8e33-2d753df09bfb_test.png", result.getThumbnailPath());
    }

    @Test
    @Transactional
    @DisplayName("아르바이트 게시물 삭제 성공")
    void deletePartTimePost_성공() {
        assertTrue(partTimePostService.deletePartTimePost(REQUEST, postAutoIncrementCount));
        assertTrue(partTimePostRepository.findById(postAutoIncrementCount).isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("아르바이트 게시물 삭제 실패 - 권한 없음 에러")
    void deletePartTimePost_실패() {
        UserEntity mockUser = new UserEntity(
                "test_id_2",
                "test_password_2",
                "test_name_2",
                "test_number_2",
                "test_address_2"
        );
        userRepository.save(mockUser);

        HttpSession session = REQUEST.getSession(false);
        assert session != null;
        session.setAttribute("userKey", -1L);

        assertThrows(PostNotAuthorizedException.class, () -> partTimePostService.deletePartTimePost(REQUEST, postAutoIncrementCount));
    }

}