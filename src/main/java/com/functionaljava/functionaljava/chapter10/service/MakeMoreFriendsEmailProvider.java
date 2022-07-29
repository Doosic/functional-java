package com.functionaljava.functionaljava.chapter10.service;

import com.functionaljava.functionaljava.chapter10.model.User;

public class MakeMoreFriendsEmailProvider implements EmailProvider{

    // 실제라면 String 이 아닌 Email 타입을 리턴할것.
    @Override
    public String getEmail(User user) {
        return "'Make More Friends' email for " + user.getName();
    }
}
