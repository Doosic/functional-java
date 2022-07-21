package com.functionaljava.functionaljava.chapter7;

import com.functionaljava.functionaljava.chapter7.model.User;

import java.util.Optional;

public class Chapter7Section1 {

    public static void main(String[] args) {
        User user1 = new User()
                .setId(101)
                .setName("Paul")
                .setVerified(true);
        User user2 = new User()
                .setId(101)
                .setName("Paul")
                .setVerified(false)
                .setEmailAddress("bob@naver.co.kr");

        // 결과는 NullPointerException 이 나와야한다.
//        System.out.println("Same? :" + userEquals(user1, user2));

        String someEmail = "some@email.com";
        String nullEmail = null;

        // 이메일이 들어있는 상자
        Optional<String> maybeEmail = Optional.of(someEmail);
        // 빈 상자
        Optional<String> maybeEmail2 = Optional.empty();
        // 널 인지 아닌지 모를때
        Optional<String> maybeEmail3 = Optional.ofNullable(someEmail);
        Optional<String> maybeEmail4 = Optional.ofNullable(nullEmail);

        // get() 을 이용하여 꺼내는 방법
        String email = maybeEmail.get();
        System.out.println(email);

        // Error 를 막기위한 방법 1.
//        String email2 = maybeEmail2.get();
        if (maybeEmail2.isPresent()){
            System.out.println(maybeEmail2.get());
        }

        // orElse 를 사용하여 maybeEmail2 가 비어있다면 defaultEmail 을 리턴
        String defaultEmail = "default@email.com";
        String email3 = maybeEmail2.orElse(defaultEmail);
        System.out.println(email3);
        String email4 = maybeEmail.orElseGet(() -> defaultEmail);
        System.out.println(email4);

        // 내가 원하는 에러를 던져줄 수 있다.
        String email5 = maybeEmail2.orElseThrow(() -> new RuntimeException("email not present"));
        System.out.println(email5);

    }

    public static boolean userEquals(User u1, User u2){
        return u1.getId() == u2.getId()
                && u1.getName().equals(u2.getName())
                && u1.getEmailAddress().equals(u2.getEmailAddress())
                && u1.isVerified() == u2.isVerified();

    }
}
