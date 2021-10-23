package org.prgrms.kyu.service;

import lombok.RequiredArgsConstructor;
import org.prgrms.kyu.dto.JoinRequest;
import org.prgrms.kyu.dto.LoginRequest;
import org.prgrms.kyu.dto.UserInfo;
import org.prgrms.kyu.entity.User;
import org.prgrms.kyu.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Transactional
    public Long join(JoinRequest form) {
        form.encodePassword(encoder.encode(form.getPassword()));
        User user = new User(form);

        final User savedUser = repository.save(user);
        return savedUser.getId();
    }

    public UserInfo login(LoginRequest loginRequest) throws AuthenticationException {
        final Optional<User> optionalUser = repository.findByEmail(loginRequest.getEmail());
        if (optionalUser.isEmpty() || !encoder.matches(loginRequest.getPassword(), optionalUser.get().getPassword())) {
            throw new AuthenticationException("회원을 찾을 수 없습니다.");
        }
        return new UserInfo(optionalUser.get());
    }
}
