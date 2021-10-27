package org.prgrms.kyu.controller.api;

import lombok.RequiredArgsConstructor;
import org.prgrms.kyu.ApiResponse;
import org.prgrms.kyu.dto.FoodRequest;
import org.prgrms.kyu.service.FoodService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class FoodRestController {

    private final FoodService foodService;

    @PostMapping("/stores/{storeId}/foods")
    public ApiResponse<Long> create(@RequestBody FoodRequest request, @PathVariable Long storeId) {
        return ApiResponse.ok(foodService.save(request, storeId));
    }
}
