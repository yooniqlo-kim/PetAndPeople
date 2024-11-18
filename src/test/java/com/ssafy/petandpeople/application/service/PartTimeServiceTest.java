package com.ssafy.petandpeople.application.service;

import com.ssafy.petandpeople.application.dto.PartTimeDto;
import com.ssafy.petandpeople.infrastructure.persistence.entity.PartTimeEntity;
import com.ssafy.petandpeople.infrastructure.persistence.entity.UserEntity;
import com.ssafy.petandpeople.infrastructure.persistence.repository.PartTimeRepository;
import com.ssafy.petandpeople.infrastructure.persistence.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
                "janedoe456",
                "securePass!2024",
                "Jane Doe",
                "987-654-2321",
                "5678 Maple Ave, Springfield, IL"
        );

        userRepository.save(mockUser);
    }

    @Test
    void createPartTimePost_성공() {
        PartTimeDto partTimeDto = new PartTimeDto(
                "Test PartTime",
                "2024-12-31",
                "5",
                "10000",
                "20-30",
                "3 months",
                "Mon, Tue",
                "09:00-18:00",
                "서울시 강남구",
                "파트타임 포지션입니다",
                "김매니저",
                "010-1234-5678"
        );

        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession(true);
        assert session != null;
        session.setAttribute("userKey", 1L);

        boolean result = partTimeService.createPartTimePost(partTimeDto, request);

        assertTrue(result);

        PartTimeEntity savedPartTimeEntity = partTimeRepository.findAll().get(0);
        assertNotNull(savedPartTimeEntity);

        assertEquals("Test PartTime", savedPartTimeEntity.getPostTitle());
        assertEquals("2024-12-31", savedPartTimeEntity.getPartTimeDeadline());
        assertEquals("5", savedPartTimeEntity.getPartTimeCount());
        assertEquals("10000", savedPartTimeEntity.getPartTimeSalary());
        assertEquals(1L, savedPartTimeEntity.getUserKey().getUserKey());
    }

    @Test
    @DisplayName("DataIntegrityViolationException_test")
    void createPartTimePost_실패() {
        PartTimeDto partTimeDto = new PartTimeDto(
                null,
                "Part-time Job Title",
                "Description of the job.",
                "10:00 AM - 2:00 PM",
                "15 USD",
                "1234 Elm Street, Springfield",
                "A company description goes here.",
                "parttime1234@company.com",
                "123-456-7890",
                "Company Name",
                "2024-11-15",
                "2024-12-15"
        );

        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        session.setAttribute("userKey", 1L);

        assertThrows(DataIntegrityViolationException.class, () -> partTimeService.createPartTimePost(partTimeDto, request));
    }

}
