package com.functionaljava.functionaljava.chapter10;

import com.functionaljava.functionaljava.chapter10.model.User;

public class Chapter10section2 {

    public static void main(String[] args) {
//        User user = User.builder(1, "Alice")
//                .withEmailAddress("alice@naver.com")
//                .withVerified(true)
//                .build();
        // 빌더를 함수형으로 재구현 한 것.
        User user = User.builder(1, "Alice")
                        .with(builder -> {
                            builder.emailAddress = "alice@naver.com";
                            builder.isVerified = true;
                        }).build();
        System.out.println(user);
    }
}
