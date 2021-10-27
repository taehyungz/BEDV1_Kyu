package org.prgrms.kyu.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import javax.naming.AuthenticationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prgrms.kyu.dto.JoinRequest;
import org.prgrms.kyu.dto.StoreCreateRequest;
import org.prgrms.kyu.dto.StoreFindResponse;
import org.prgrms.kyu.entity.Store;
import org.prgrms.kyu.entity.UserType;
import org.prgrms.kyu.repository.StoreRepository;
import org.prgrms.kyu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class StoreServiceTest {

  @Autowired
  StoreService storeService;

  @Autowired
  UserService userService;

  @Autowired
  StoreRepository storeRepository;

  @Autowired
  UserRepository userRepository;

  Long userId;
  Long storeId;
  StoreCreateRequest storeCreateRequest;

  @BeforeEach
  public void setUp() throws AuthenticationException {
    userId = userService.join(
        new JoinRequest("test1@test.com", "1234", "user", "nick", "Seoul", "STORE_OWNER"));
    storeCreateRequest = new StoreCreateRequest(
        "맘스터치",
        "01011112222",
        "맘스터치입니다.",
        "Seoul",
        userId);
    storeId = storeService.save(storeCreateRequest);
  }

  @AfterEach
  public void cleanUp(){
    storeService.deleteAll();
    userRepository.deleteAll();
  }



  @Test
  @DisplayName("음식점을 생성할 수 있다.")
  public void storeSaveTest() throws AuthenticationException {
    //then
    Optional<Store> findStore = storeRepository.findById(storeId);
    Store store = storeCreateRequest.convertToStore(userService.findById(userId));
    assertThat(findStore.get(),notNullValue());
    StoreCreateRequest findStoreRequest = new StoreCreateRequest(
        store.getName(),
        store.getTelephone(),
        store.getDescription(),
        store.getLocation(),
        store.getUser().getId());
    assertThat(findStoreRequest,allOf(notNullValue(),samePropertyValuesAs(storeCreateRequest)));
  }



  @Test
  @DisplayName("id로 음식점을 찾을 수 있다.")
  public void findByIdTest() throws NotFoundException {
    //when
    StoreFindResponse findStore = storeService.findById(storeId);

    //then
    Optional<Store> store = storeRepository.findById(storeId);
    assertThat(findStore,allOf(notNullValue(),samePropertyValuesAs(new StoreFindResponse(store.get()))));
  }



  @Test
  @DisplayName("모든 음식점을 찾을 수 있다.")
  public void findAllTest() throws AuthenticationException {
    Long userId2 = userService.join(
        new JoinRequest("test2@test.com", "1234", "user2", "nick2", "Seoul", "STORE_OWNER"));
    StoreCreateRequest storeCreateRequest2 = new StoreCreateRequest(
        "맘스터치2",
        "01011112222",
        "맘스터치2입니다.",
        "Seoul",
        userId2);
    storeService.save(storeCreateRequest2);

    //when
    List<StoreFindResponse> all = storeService.findAll();

    //then
    assertThat(all,hasSize(2));
  }

}
