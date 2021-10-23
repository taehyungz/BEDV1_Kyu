package org.prgrms.kyu.dto;

import org.prgrms.kyu.entity.User;

public class UserInfo {
    private String email;
    private String name;
    private String nickname;
    private Long balance;
    private String address;

    public UserInfo(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.balance = user.getBalance();
        this.address = user.getAddress();
    }
}
