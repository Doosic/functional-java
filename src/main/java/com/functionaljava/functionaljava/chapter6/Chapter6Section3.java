package com.functionaljava.functionaljava.chapter6;

import com.functionaljava.functionaljava.chapter6.model.Order;
import com.functionaljava.functionaljava.chapter6.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.functionaljava.functionaljava.chapter6.model.Order.OrderStatus.*;

public class Chapter6Section3 {

    public static void main(String[] args) {
        List<Integer> numberList = Arrays.asList(3, 6, -4);
        List<Integer> numberStream = numberList.stream()
                .map(x -> x * 2)
                .collect(Collectors.toList());
        System.out.println(numberStream);

        List<String> numberListStream = numberList.stream()
                .map(x -> "Number is " + x)
                .collect(Collectors.toList());
        System.out.println(numberListStream);

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
        간단하게 바꾸기 전의 방법 (사용자의 이메일만 추출하기)
        Stream<User> userStream = users.stream();
        Stream<String> userEmailStream = userStream.map(User::getEmailAddress);
        List<String> emailAddresses = userEmailStream.collect(Collectors.toList());
    */

        List<String> userEmils = users.stream()
                .map(User::getEmailAddress)
                .collect(Collectors.toList());
        System.out.println(userEmils);

        Order order1 = new Order()
                .setId(1001)
                .setStatus(CREATED)
                .setCreatedByUserId(101);
        Order order2 = new Order()
                .setId(1002)
                .setStatus(ERROR)
                .setCreatedByUserId(103);
        Order order3 = new Order()
                .setId(1003)
                .setStatus(PROCESSED)
                .setCreatedByUserId(102);
        Order order4 = new Order()
                .setId(1004)
                .setStatus(ERROR)
                .setCreatedByUserId(104);
        Order order5 = new Order()
                .setId(1005)
                .setStatus(IN_PROGRESS)
                .setCreatedByUserId(101);
        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        // TODO: 어떠한 유저가 만들었는지 유저의 리스트를 만들어보자
        List<Long> orderUsers = orders.stream()
                .map(Order::getCreatedByUserId)
                .collect(Collectors.toList());
        System.out.println(orderUsers);

    }
}
