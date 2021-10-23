package org.prgrms.kyu.controller;

import lombok.RequiredArgsConstructor;
import org.prgrms.kyu.ApiResponse;
import org.prgrms.kyu.dto.JoinRequest;
import org.prgrms.kyu.dto.LoginRequest;
import org.prgrms.kyu.dto.UserInfo;
import org.prgrms.kyu.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ApiResponse<UserInfo> login(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        return ApiResponse.ok(userService.login(loginRequest));
    }

    @PostMapping("/join")
    public ApiResponse<Long> join(@RequestBody JoinRequest joinRequest) {
        return ApiResponse.ok(userService.join(joinRequest));
    }
}
