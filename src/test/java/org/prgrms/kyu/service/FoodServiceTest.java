package org.prgrms.kyu.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.prgrms.kyu.dto.FoodRequest;
import org.prgrms.kyu.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class FoodServiceTest {
    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodRepository foodRepository;

    @AfterEach
    void clear() {
        foodRepository.deleteAll();
    }

    @Test
    void save() {
        //Given
        FoodRequest request = FoodRequest.builder()
                .name("test name")
                .description("test description")
                .price(1000)
                .build();

        //When
        foodService.save(request, 2L);

        //Then
        assertThat(foodRepository.findAll().isEmpty(), is(false));
    }
}
