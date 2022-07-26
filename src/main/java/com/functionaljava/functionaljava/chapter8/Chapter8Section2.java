package com.functionaljava.functionaljava.chapter8;

import com.functionaljava.functionaljava.chapter8.model.Order;
import com.functionaljava.functionaljava.chapter8.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.functionaljava.functionaljava.chapter8.model.Order.OrderStatus.*;

public class Chapter8Section2 {

    public static void main(String[] args) {
        // 다음 numbers 에 담긴 숫자들이 모두 양수라면 true 하나라도 아니라면 false
        List<Integer> numbers = Arrays.asList(3, -4, 2, 7, 9);
        boolean allPostive = numbers.stream()
                .allMatch(number -> number > 0);
        System.out.println("Are all numbers positive: " + allPostive);

        // numbers 안에 담긴 숫자들중 하나라도 음수라면 true
        boolean anyNegative = numbers.stream()
                .anyMatch(number -> number < 0);
        System.out.println("Is any number negative" + anyNegative);

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

        // 모든 유저가 검증이 됐는지 확인
        boolean areAllUserVerified = users.stream()
                .allMatch(User::isVerified);
        System.out.println(areAllUserVerified);

        Order order1 = new Order()
                .setId(1001)
                .setAmount(BigDecimal.valueOf(2000))
                .setStatus(CREATED);
        Order order2 = new Order()
                .setId(1002)
                .setAmount(BigDecimal.valueOf(4000))
                .setStatus(ERROR);
        Order order3 = new Order()
                .setId(1003)
                .setAmount(BigDecimal.valueOf(3000))
                .setStatus(PROCESSED);
        Order order4 = new Order()
                .setId(1004)
                .setAmount(BigDecimal.valueOf(7000))
                .setStatus(ERROR);
        Order order5 = new Order()
                .setId(1005)
                .setAmount(BigDecimal.valueOf(8000))
                .setStatus(IN_PROGRESS);
        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        // TODO: check if any of orders is in ERROR status
        boolean isanyOrderInErrorStatus = orders.stream()
                .anyMatch(order -> order.getStatus() == ERROR);
        System.out.println(isanyOrderInErrorStatus);
    }
}
