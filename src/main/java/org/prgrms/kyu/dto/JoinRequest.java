package org.prgrms.kyu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JoinRequest {
    String email;
    String password;
    String name;
    String nickname;
    String address;

    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
