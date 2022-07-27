package com.functionaljava.functionaljava.chapter8;

import com.functionaljava.functionaljava.chapter8.model.User;
import com.functionaljava.functionaljava.chapter8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chapter8Section10 {

    public static void main(String[] args) {
        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(true)
                .setEmailAddress("alice@naver.co.kr");
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setEmailAddress("bob@naver.co.kr");
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setEmailAddress("charlie@naver.co.kr");
        User user4 = new User()
                .setId(104)
                .setName("David")
                .setVerified(false)
                .setEmailAddress("David@naver.co.kr");
        User user5 = new User()
                .setId(105)
                .setName("Charse")
                .setVerified(false)
                .setEmailAddress("David@naver.co.kr");
        User user6 = new User()
                .setId(106)
                .setName("Lailli")
                .setVerified(false)
                .setEmailAddress("David@naver.co.kr");
        List<User> users = Arrays.asList(user1, user2, user3, user4);

        // 병렬처리 하기 전 약 27ms ~ 60ms
        long startTime = System.currentTimeMillis();
        EmailService emailService = new EmailService();
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmailEmail);
        long endTime = System.currentTimeMillis();
        System.out.println("Sequential: "+(endTime - startTime) + "ms");

        // 병렬처리 적용 약 7ms ~ 11ms, 그러나 순서가 뒤죽박죽
        startTime = System.currentTimeMillis();
        users.stream().parallel()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmailEmail);
        endTime = System.currentTimeMillis();
        System.out.println("Sequential: "+(endTime - startTime) + "ms");

        List<User> processedUsers = users.parallelStream()
                .map(user -> {
                    System.out.println("Capitalize user name for user " + user.getId());
                    user.setName(user.getName().toUpperCase());
                    return user;
                })
                .map(user -> {
                    System.out.println("Set 'is Verified' to true for user " + user.getId());
                    user.setVerified(true);
                    return user;
                })
                .collect(Collectors.toList());
        System.out.println(processedUsers);
        /*
            주의할 점으로는 중간처리 과정이 순서가 매우 중요하다면
            병렬처리 사용이 불가능. mutex 를 사용하여 처리할 수 있지만
            속도가 그냥 처리하는것보다 느려질 가능성이 있다.
         */
    }
}
