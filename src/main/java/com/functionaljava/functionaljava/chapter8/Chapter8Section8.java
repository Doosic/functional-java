package com.functionaljava.functionaljava.chapter8;

import com.functionaljava.functionaljava.chapter8.model.Order;
import com.functionaljava.functionaljava.chapter8.model.Order.OrderStatus;
import com.functionaljava.functionaljava.chapter8.model.User;
import com.functionaljava.functionaljava.chapter8.service.EmailService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.functionaljava.functionaljava.chapter8.model.Order.OrderStatus.*;

public class Chapter8Section8 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
        Map<Boolean, List<Integer>> numberPartitions = numbers.stream()
                .collect(Collectors.partitioningBy(number -> number % 2 == 0));
        System.out.println("Even number: " + numberPartitions.get(true));   // 짝수 그룹 출력
        System.out.println("Odd number: " + numberPartitions.get(false));   // 홀수 그룹 출력

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(true)
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204, 205, 207))
                .setEmailAddress("alice@naver.co.kr");
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setFriendUserIds(Arrays.asList(204, 205, 206))
                .setEmailAddress("bob@naver.co.kr");
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setFriendUserIds(Arrays.asList(204, 205, 207))
                .setEmailAddress("charlie@naver.co.kr");
        User user4 = new User()
                .setId(104)
                .setName("David")
                .setVerified(false)
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204))
                .setEmailAddress("David@naver.co.kr");
        List<User> users = Arrays.asList(user1, user2, user3, user4);

        // TODO: 친구가 5명 초과라면 친구랑 놀아보세요, 이하라면 친구를 더 사귀어보세요 라는 이메일을 보내보자
        Map<Boolean, List<User>> userPartitions = users.stream()
                .collect(Collectors.partitioningBy(user -> user.getFriendUserIds().size() > 5));

        EmailService emailService = new EmailService();

        for (User user: userPartitions.get(true)) {
            emailService.sendPlayWithFriendsEmail(user);
        }

        for (User user: userPartitions.get(false)) {
            emailService.sendMakeMoreFriendsEmail(user);
        }
    }
}
