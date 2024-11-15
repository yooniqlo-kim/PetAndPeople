package com.ssafy.petandpeople.application.service;

import com.ssafy.petandpeople.application.dto.PartTimePostDto;
import com.ssafy.petandpeople.common.exception.job.PostNotFoundException;
import com.ssafy.petandpeople.infrastructure.persistence.entity.PartTimePostEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.PartTimeRepository;
import com.ssafy.petandpeople.infrastructure.persistence.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(value = "test")
public class PartTimeServiceTest {

    private final PartTimeService partTimeService;
    private final PartTimeRepository partTimeRepository;
    private final UserRepository userRepository;

    @Autowired
    public PartTimeServiceTest(PartTimeService partTimeService, PartTimeRepository partTimeRepository, UserRepository userRepository) {
        this.partTimeService = partTimeService;
        this.partTimeRepository = partTimeRepository;
        this.userRepository = userRepository;
    }

    @BeforeAll
    void setUp() {
        UserEntity mockUser = new UserEntity(
                "kim_ssafy123@naver.com",
                "password1234!",
                "kim_ssafy",
                "010-3456-7890",
                "서울특별시 강남구 역삼동 123-45"
        );

        userRepository.save(mockUser);
    }

    @Test
    void createPartTimePost_성공() {
        PartTimePostDto createPartTimePostDto = new PartTimePostDto(
                "카페 알바 모집",
                "2024-12-31",
                "5",
                "12000",
                "20-30",
                "3개월",
                "토,일",
                "09:00-18:00",
                "서울특별시 강남구 테헤란로 123",
                "주말 파트타임 채용 포지션입니다.",
                "박매니저",
                "010-9876-5432"
        );

        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession(true);
        assert session != null;
        session.setAttribute("userKey", 1L);

        assertTrue(partTimeService.createPartTimePost(createPartTimePostDto, request));

        PartTimePostEntity savedPartTimePostEntity = partTimeRepository.findById(1L).get();

        assertEquals("카페 알바 모집", savedPartTimePostEntity.getPostTitle());
        assertEquals("2024-12-31", savedPartTimePostEntity.getPartTimeDeadline());
        assertEquals("5", savedPartTimePostEntity.getPartTimeCount());
        assertEquals("12000", savedPartTimePostEntity.getPartTimeSalary());
        assertEquals(1L, savedPartTimePostEntity.getUserKey().getUserKey());
        assertEquals(1L, savedPartTimePostEntity.getPostKey());
    }

    @Test
    void updatePartTimePost_성공() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession(true);
        assert session != null;
        session.setAttribute("userKey", 1L);

        PartTimePostDto createPartTimePostDto = new PartTimePostDto(
                "식당 알바 모집",
                "2024-11-30",
                "2",
                "14000",
                "30-40",
                "1년",
                "월,수,금",
                "13:00-18:00",
                "서울특별시 강남구 테헤란로 456",
                "평일 파트타임 채용 포지션입니다.",
                "김매니저",
                "010-1234-4132"
        );

        partTimeService.createPartTimePost(createPartTimePostDto, request);

        PartTimePostDto updatePartTimePostDto = new PartTimePostDto(
                "카페 바리스타 모집",
                "2024-12-15",
                "3",
                "15000",
                "25-35",
                "6개월",
                "화,목,토",
                "10:00-15:00",
                "서울특별시 마포구 홍익로 10",
                "주말 및 평일 파트타임 채용 포지션입니다.",
                "이매니저",
                "010-9876-5432"
        );

        Long postKey = (long) partTimeRepository.findAll().size();

        assertTrue(partTimeService.updatePartTimePost(postKey, updatePartTimePostDto, request));
        PartTimePostEntity updatedPartTimePostEntity = partTimeRepository.findById(postKey).orElseThrow(PostNotFoundException::new);

        assertEquals("카페 바리스타 모집", updatedPartTimePostEntity.getPostTitle());
        assertEquals("2024-12-15", updatedPartTimePostEntity.getPartTimeDeadline());
        assertEquals("3", updatedPartTimePostEntity.getPartTimeCount());
        assertEquals("15000", updatedPartTimePostEntity.getPartTimeSalary());
        assertEquals(1L, updatedPartTimePostEntity.getUserKey().getUserKey());
    }

}