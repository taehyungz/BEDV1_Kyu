package org.prgrms.kyu.service;

import lombok.RequiredArgsConstructor;
import org.prgrms.kyu.dto.FoodRequest;
import org.prgrms.kyu.repository.FoodRepository;
import org.prgrms.kyu.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Long save(FoodRequest request, Long storeId) {
        var food = foodRepository.save(FoodRequest.convertToFood(request));
        food.update(storeRepository.getById(storeId));
        return food.getId();
    }

}
