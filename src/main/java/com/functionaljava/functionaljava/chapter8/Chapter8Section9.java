package com.functionaljava.functionaljava.chapter8;

import com.functionaljava.functionaljava.chapter8.model.User;
import com.functionaljava.functionaljava.chapter8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chapter8Section9 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
//        numbers.stream().forEach(number -> System.out.println("The number is " + number));
        // stream 이 아닌 List, Set 타입에도 iterable 이 적용되어 있기에 stream() 생략가능.
//        numbers.forEach(number -> System.out.println("The number is " + number));

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
        List<User> users = Arrays.asList(user1, user2, user3, user4);

        EmailService emailService = new EmailService();
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmailEmail);
        // forEach 안에 => .forEach(user -> emailService.sendVerifyYourEmailEmail(user));
        // 그러나 MethodReference 사용가능 .forEach(emailService::sendVerifyYourEmailEmail);

        /*
            for문 안에서 인덱스를 사용해야한다면? for(int i = 0; i < ...size(); i++) {...}
            어떤 범위의 숫자를 스트림을 이용하여 제공해주는 것이 있다.
            IntStream.range(시작점, 끝점); for 문과 동일하게 시작점은 포함하고 끝점은 포함하지 않는다.
        */
        IntStream.range(0, users.size()).forEach(i -> {
            User user = users.get(i);
            System.out.println("Do an operation on user " + user.getName() + " at index " + i);
        });

    }
}
