package org.prgrms.kyu.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.prgrms.kyu.entity.User;
import org.prgrms.kyu.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository repository;

  public User findById(Long id) throws NotFoundException {
    return repository.findById(id)
        .orElseThrow(() -> new NotFoundException("사용자 정보를 찾을 수 없습니다."));
  }
}
