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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles(value = "test")
public class PartTimeServiceTest {
    private static final MockHttpServletRequest REQUEST = new MockHttpServletRequest();

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
                "test_id",
                "test_password",
                "test_name",
                "test_number",
                "test_address"
        );
        userRepository.save(mockUser);

        UserEntity useKey = userRepository.findById(1L).get();
        PartTimePostEntity createPartTimeEntity = new PartTimePostEntity(
                useKey,
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
        partTimeRepository.save(createPartTimeEntity);

        HttpSession session = REQUEST.getSession(true);
        assert session != null;
        session.setAttribute("userKey", 1L);
    }

    @Test
    void createPartTimePost_성공() {
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
                "test_manager_phone_number_2"
        );

        assertTrue(partTimeService.createPartTimePost(createPartTimePostDto, REQUEST));
    }

    @Test
    void updatePartTimePost_성공() {
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
                "test_manager_phone_number_3"
        );

        assertTrue(partTimeService.updatePartTimePost(1L, updatePartTimePostDto, REQUEST));

        PartTimePostEntity updatedPartTimePostEntity = partTimeRepository.findById(1L).orElseThrow(PostNotFoundException::new);
        assertEquals("test_post_tile_3", updatedPartTimePostEntity.getPostTitle());
        assertEquals("test_deadline_3", updatedPartTimePostEntity.getPartTimeDeadline());
        assertEquals("test_count_3", updatedPartTimePostEntity.getPartTimeCount());
        assertEquals("test_salary_3", updatedPartTimePostEntity.getPartTimeSalary());
    }

    @Test
    void selectPartTimePostByUserKey_성공() {
        assertNotNull(partTimeService.selectPartTimePosyByUserKey(REQUEST));
    }

    @Test
    void selectAllPartTimePostByUserKey_성공() {
        assertNotNull(partTimeService.selectAllPartTimePost());
    }

}