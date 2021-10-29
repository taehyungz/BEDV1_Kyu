package org.prgrms.kyu.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.prgrms.kyu.dto.FoodRequest;
import org.prgrms.kyu.entity.Food;
import org.prgrms.kyu.entity.Store;
import org.prgrms.kyu.repository.FoodRepository;
import org.prgrms.kyu.repository.StoreRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class FoodServiceTest {
    @InjectMocks
    private FoodService foodService;

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private StoreRepository storeRepository;

    @Test
    void save() {
        //Given
        Long fakeStoreId = 1L;

        Store store = Store.builder()
                .id(fakeStoreId)
                .description("test Description")
                .location("test location")
                .telephone("010")
                .build();


        FoodRequest request = FoodRequest.builder()
                .name("test name")
                .description("test description")
                .price(1000)
                .build();

        Food food = FoodRequest.convertToFood(request);
        food.update(store);

        Long fakeFoodId = 1L;
        ReflectionTestUtils.setField(food, "id", fakeFoodId);


        given(foodRepository.save(any()))
                .willReturn(food);
        given(storeRepository.findById(fakeStoreId))
                .willReturn(Optional.ofNullable(store));
        given(foodRepository.findById(fakeFoodId))
                .willReturn(Optional.ofNullable(food));

        //When
        Long newFoodId = foodService.save(request, fakeStoreId);

        //Then
        Food findFood = foodRepository.findById(newFoodId).get();

        assertEquals(food.getId(), findFood.getId());
    }

    @Test
    void getAll() {
        //Given
        Long fakeStoreId = 1L;

        Store store = Store.builder()
                .id(fakeStoreId)
                .description("test Description")
                .location("test location")
                .telephone("010")
                .build();


        FoodRequest request = FoodRequest.builder()
                .name("test name")
                .description("test description")
                .price(1000)
                .build();

        Food food = FoodRequest.convertToFood(request);
        food.update(store);

        Long fakeFoodId = 1L;
        ReflectionTestUtils.setField(food, "id", fakeFoodId);

        //When

        //Then
    }
}
