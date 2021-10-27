package org.prgrms.kyu.service;

import java.util.List;
import java.util.stream.Collectors;
import javassist.NotFoundException;
import javax.naming.AuthenticationException;
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
        .map(StoreFindResponse::new)
        .collect(Collectors.toList());
  }

  public StoreFindResponse findById(Long id) throws NotFoundException {
    return repository
        .findById(id)
        .map(StoreFindResponse::new)
        .orElseThrow(() ->
            new NotFoundException("음식점 정보를 찾을 수 없습니다."));
  }

  @Transactional
  public Long save(StoreCreateRequest storeCreateRequest) throws AuthenticationException {
    return repository.save(
            storeCreateRequest.convertToStore(
                userService.findById(
                    storeCreateRequest.getUserId())))
        .getId();
  }

  @Transactional
  public void deleteAll(){
    repository.deleteAll();
  }



}
