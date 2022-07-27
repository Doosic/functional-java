package com.functionaljava.functionaljava.chapter8;

import com.functionaljava.functionaljava.chapter8.model.Order;
import com.functionaljava.functionaljava.chapter8.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.functionaljava.functionaljava.chapter8.model.Order.OrderStatus.*;
import static java.util.function.Function.identity;

public class Chapter8Section6 {

    public static void main(String[] args) {
        // Map 형태로 변환이 가능하다. 첫번째 인자로 Key, 두번째 인자로 Value
        // 첫번째 인자가 x -> x 같은 단순한 함수의 경우 Function.identity() 로 대체 가능
        Map<Integer, String> numberMap = Stream.of(3, 5, -4, 2, 6)
                .collect(Collectors.toMap(identity(), x -> "Number is " + x));
        System.out.println(numberMap.get(3));

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
        // key값은 오퍼레이터를 사용하여 id를 지정. value값은 x -> x 즉, 유저 그대로 넣기
        Map<Integer, User> userIdToUserMap = users.stream()
                .collect(Collectors.toMap(User::getId, identity()));
        System.out.println(userIdToUserMap);
        System.out.println(userIdToUserMap.get(103));

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

        // TODO: Create a map from order id to order status
        Map<Long, Order.OrderStatus> orderIdToOrderStatusMap = orders.stream()
                .collect(Collectors.toMap(Order::getId, Order::getStatus)); // => 둘다 오퍼레이터를 써볼 생각을 못했네 ㄷㄷ;
        System.out.println(orderIdToOrderStatusMap);
    }
}
