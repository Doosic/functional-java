package com.functionaljava.functionaljava.chapter8;

import com.functionaljava.functionaljava.chapter8.model.Order;
import com.functionaljava.functionaljava.chapter8.model.OrderLine;
import com.functionaljava.functionaljava.chapter8.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.functionaljava.functionaljava.chapter8.model.Order.OrderStatus.*;

public class Chapter8Section4 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 4, -2, -5, 3);
        // BinaryOperator 를 넘겨준다.
        // 첫번째 결과값을 가지고 계속 계산하는 함수 1+4 = 5, 5 + -2 = 3, 3 + -5 = -2, -2 + 3 = 1
        int sum = numbers.stream()
                .reduce((x,y) -> x + y)
                .get();
        System.out.println(sum);

        // Max, Min, Count 도 reduce의 일종이다.
        int min = numbers.stream()
                .reduce((x,y) -> x > y ? x: y)
                .get();
        System.out.println(min);


        // 초기값을 제공하는 reduce. 항상 값을 리턴하기때문에 get() 을 사용할 필요가 없다.
        /*
            값이 없는 Optional에 get() 을 사용한다면 NoSuchElementException 이 발생한다.
            그렇기에 다음과 같은 방법을 통해서 기본값을 주거나 예외를 던져주자.
            Person person = findById(4).orElseThrow(PersonNotFoundException::new);
            String name = person.getName();
         */
        int product = numbers.stream()
                .reduce(1, (x,y) -> x * y);
        System.out.println(product);
        
        List<String> numberStrList = Arrays.asList("3", "2", "5", "-4");
        int sumOfNumberStrList = numberStrList.stream()
                .map(Integer::parseInt)
                .reduce(0, (x,y) -> x + y);
        System.out.println(sumOfNumberStrList);

        // 이건 잘 쓰이지않음 왠만하면 맵과 reduce를 따로 사용 그냥 있다는것만 알아두자
        int sumOfNumberStrList2 = numberStrList.stream()
                .reduce(0,(number, str) -> number + Integer.parseInt(str), (num1, num2) -> num1 + num2);
        System.out.println(sumOfNumberStrList2);


        // 예제를 통한 학습
        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(true)
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204));
        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setFriendUserIds(Arrays.asList(204, 205, 206));
        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(false)
                .setFriendUserIds(Arrays.asList(204, 205, 207));
        User user4 = new User()
                .setId(104)
                .setName("David")
                .setVerified(false)
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204));
        List<User> users = Arrays.asList(user1, user2, user3, user4);

        // 친구수의 총 합 계산하기, default 값을 주는것이 좋다.
        int sumOfNumberOfFriends = users.stream()
                .map(User::getFriendUserIds)
                .map(List::size)
                .reduce(0, (x,y) -> x + y);
        System.out.println(sumOfNumberOfFriends);


        Order order1 = new Order()
                .setId(1001)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(2000))
                ));
        Order order2 = new Order()
                .setId(1002)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(2000)),
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(3000))
                ));
        Order order3 = new Order()
                .setId(1003)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(4000))
                ));
        Order order4 = new Order()
                .setId(1004)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(4000)),
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(5000))
                ));
        Order order5 = new Order()
                .setId(1005)
                .setOrderLines(Arrays.asList(
                new OrderLine()
                        .setAmount(BigDecimal.valueOf(2000)),
                new OrderLine()
                        .setAmount(BigDecimal.valueOf(3000))
        ));
        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        // TODO: find the sum of amounts
        BigDecimal sumOfAmount = orders.stream()
                .map(Order::getOrderLines)  // Stream<List<Orderline>>
                .flatMap(List::stream)  // Stream<OrderLine>
                .map(OrderLine::getAmount)  // Stream<BigDecimal>
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sumOfAmount);
    }
}
