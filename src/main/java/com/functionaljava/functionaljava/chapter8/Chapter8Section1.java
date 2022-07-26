package com.functionaljava.functionaljava.chapter8;

import com.functionaljava.functionaljava.chapter8.model.Order;
import com.functionaljava.functionaljava.chapter8.model.User;
import jdk.swing.interop.SwingInterOpUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.functionaljava.functionaljava.chapter8.model.Order.OrderStatus.*;

public class Chapter8Section1 {

    public static void main(String[] args) {
        Optional<Integer> max = Stream.of(5, 3, 6, 2, 1)
                .max(Integer::compareTo);
        System.out.println(max.get());

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
        List<User> users = Arrays.asList(user1, user2, user3);

        User firstUser = users.stream()
                .min((u1,u2) -> u2.getName().compareTo(u1.getName()))
                .get();
        System.out.println(firstUser);

        long positiveIntegerCount = Stream.of(1, -4, 5, -3, 6)
                .filter(x -> x > 0)
                .count();
//        System.out.println(positiveIntegerCount);

        /*
            최근 24간 이내에 가입한 유저중에서 아직 검증되지 않은 유저들이 총 몇명인지 숫자를 알아보자
        */
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        user1.setCreatedAt(now.minusDays(2));
        user2.setCreatedAt(now.minusHours(10));
        user3.setCreatedAt(now.minusHours(1));
        user4.setCreatedAt(now.minusHours(27));
        users = Arrays.asList(user1, user2, user3, user4);

        long unverfiedUsersIn24Hrs = users.stream()
                .filter(user -> user.getCreatedAt().isAfter(now.minusDays(1)))
                .filter(user -> !user.isVerified())
                .count();
        System.out.println(unverfiedUsersIn24Hrs);

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

        // TODO: find order with highest amount an in ERROR status
        Order highestAmountOrder = orders.stream()
                .filter(order -> order.getStatus() == ERROR)
                .max((o1,o2) -> o1.getAmount().compareTo(o2.getAmount()))
                .get();
        System.out.println(highestAmountOrder);

        BigDecimal maxErroredAmount = orders.stream()
                .filter(order -> order.getStatus() == ERROR)
                .map(Order::getAmount)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);   // orElse 를 통해 아무것도 없을때에 default 값으로 0을 넘겨준다.
        System.out.println(maxErroredAmount);
    }
}
