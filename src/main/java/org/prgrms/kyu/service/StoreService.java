package org.prgrms.kyu.service;

import java.util.List;
import java.util.stream.Collectors;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.prgrms.kyu.dto.StoreCreateRequest;
import org.prgrms.kyu.dto.StoreFindResponse;
import org.prgrms.kyu.entity.Store;
import org.prgrms.kyu.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {
  private final StoreRepository repository;
  private final UserService userService;


  public List<StoreFindResponse> findAll() {
    return repository
        .findAll()
        .stream()
        .map((item) ->
            new StoreFindResponse(
                item.getId(),
                item.getName(),
                item.getTelephone(),
                item.getDescription(),
                item.getLocation()))
        .collect(Collectors.toList());
  }

  public StoreFindResponse findById(Long id) throws NotFoundException {
    return repository
        .findById(id)
        .map((item) ->
            new StoreFindResponse(
                item.getId(),
                item.getName(),
                item.getTelephone(),
                item.getDescription(),
                item.getLocation()))
        .orElseThrow(() ->
            new NotFoundException("음식점 정보를 찾을 수 없습니다."));
  }

  @Transactional
  public Long save(StoreCreateRequest storeCreateRequest) throws NotFoundException {
    return repository.save(
            Store.builder()
                .name(storeCreateRequest.getName())
                .telephone(storeCreateRequest.getTelephone())
                .description(storeCreateRequest.getDescription())
                .location(storeCreateRequest.getLocation())
                .user(userService.findById(storeCreateRequest.getUserId()))
                .build())
        .getId();
  }



}
