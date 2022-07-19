package com.functionaljava.functionaljava.chapter6;

import com.functionaljava.functionaljava.chapter6.model.Order;
import com.functionaljava.functionaljava.chapter6.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.functionaljava.functionaljava.chapter6.model.Order.OrderStatus.*;

public class Chapter6Section4 {

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
        List<User> users = Arrays.asList(user1, user2, user3);

        /*
        # 명령형 프로그래밍의 검증과정 및 이메일을 추출하는 모습
            List<String> emails = new ArrayList<>();
            for (User user: users){
                if(!user.isVerified()){
                    String email = user.getEmailAddress();
                    emails.add(email);
                }
            }
            System.out.println(emails);
        */

        // 함수형 프로그래밍의 검증과정 및 이메일을 추출하는 모습
        List<String> emails2 = users.stream()
                .filter(user -> !user.isVerified())
                .map(User::getEmailAddress)
                .collect(Collectors.toList());
        System.out.println(emails2);


        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = new Order()
                .setId(1001)
                .setStatus(CREATED)
                .setCreatedByUserId(101)
                .setCreateAt(now.minusHours(4));
        Order order2 = new Order()
                .setId(1002)
                .setStatus(ERROR)
                .setCreatedByUserId(103)
                .setCreateAt(now.minusHours(1));
        Order order3 = new Order()
                .setId(1003)
                .setStatus(PROCESSED)
                .setCreatedByUserId(102)
                .setCreateAt(now.minusHours(36));
        Order order4 = new Order()
                .setId(1004)
                .setStatus(ERROR)
                .setCreatedByUserId(104)
                .setCreateAt(now.minusHours(15));
        Order order5 = new Order()
                .setId(1005)
                .setStatus(IN_PROGRESS)
                .setCreatedByUserId(101)
                .setCreateAt(now.minusHours(10));
        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        // TODO: Find orders in Error status, and extract createdByUserIds as a list
        List<Long> createErrorIds = orders.stream()
                .filter(order -> order.getStatus() == ERROR)
                .map(Order::getCreatedByUserId)
                .collect(Collectors.toList());
        System.out.println(createErrorIds);

        // TODO: Find orders in ERROR status and created within 24 hours
        // 필터를 2번써도 되고 && 사용해도 된다.
        List<Order> OrdersInErrorStatusIn24hrs = orders.stream()
                .filter(order -> order.getStatus() == ERROR)
                .filter(order -> order.getCreateAt().isAfter(now.minusHours(24)))
                .collect(Collectors.toList());
        System.out.println(OrdersInErrorStatusIn24hrs);

    }
}
