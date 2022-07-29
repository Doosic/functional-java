package com.functionaljava.functionaljava.chapter10.service;

import com.functionaljava.functionaljava.chapter10.model.User;

public interface EmailProvider {
    // 예제이기에 이메일을 단순 스트링을 리턴
    String getEmail(User user);
}
