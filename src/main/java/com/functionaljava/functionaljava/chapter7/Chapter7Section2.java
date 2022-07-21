package com.functionaljava.functionaljava.chapter7;

import com.functionaljava.functionaljava.chapter7.model.User;

import java.util.Optional;

public class Chapter7Section2 {

    public static void main(String[] args) {
        // ofNullable - Null 인지 아닌지 알 지 못하는 오브젝트로 Optional 을 만들 때
        // ifPresent null 이 아니라면 action 을 실행
        Optional<User> maybeUser = Optional.ofNullable(maybeGetUser(true));
        maybeUser.ifPresent(user -> System.out.println(user));

        // Optional 이 null mapper 를 적용
        Optional<Integer> maybeId = Optional.ofNullable(maybeGetUser(true))
                .map(user -> user.getId());
        maybeId.ifPresent(System.out::println);

        String maybeUserName = Optional.ofNullable(maybeGetUser(true))
                .map(User::getName)
                .map(name -> "The name is " + name)
                .orElse("Name is Empty");
        System.out.println(maybeUserName);

        // getEmailAddress 의 리턴타입이 Optional 이기에 Optional<Optional<String>> 이 되는것을 한번 꺼내준다.
        Optional<String> maybeEmail = Optional.ofNullable(maybeGetUser(false))
                .flatMap(User::getEmailAddress);
        System.out.println(maybeEmail);
    }
    public static User maybeGetUser(boolean returnUser) {
        if (returnUser){
            return new User()
                    .setId(101)
                    .setName("Paul")
                    .setVerified(false)
                    .setEmailAddress("bob@naver.co.kr");
        }
        return null;
    }
}
