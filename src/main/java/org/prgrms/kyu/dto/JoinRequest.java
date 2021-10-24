package org.prgrms.kyu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JoinRequest {
    String email;
    String password;
    String name;
    String nickname;
    String address;
    String type;

    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
