package org.prgrms.kyu.controller;

import java.util.List;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.prgrms.kyu.ApiResponse;
import org.prgrms.kyu.dto.StoreFindResponse;
import org.prgrms.kyu.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {
  private final StoreService storeService;

  @GetMapping("/api/v1/store")
  public ApiResponse<List<StoreFindResponse>> getAllStore(){
    return ApiResponse.ok(storeService.findAll());
  }

  @GetMapping("/api/v1/store/{id}")
  public ApiResponse<StoreFindResponse> getOneStore(@PathVariable Long id)
      throws NotFoundException {
    return ApiResponse.ok(storeService.findById(id));
  }


}
