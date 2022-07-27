package com.functionaljava.functionaljava.chapter8;

import com.functionaljava.functionaljava.chapter8.model.Order;
import com.functionaljava.functionaljava.chapter8.model.Order.OrderStatus;
import com.functionaljava.functionaljava.chapter8.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.functionaljava.functionaljava.chapter8.model.Order.OrderStatus.*;
import static java.util.function.Function.identity;

public class Chapter8Section7 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
        Map<Integer, List<Integer>> unitDigitMap = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10));
        System.out.println(unitDigitMap);

        Map<Integer, Set<Integer>> unitDigitSet = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10, Collectors.toSet()));
        System.out.println(unitDigitSet);

        Map<Integer, List<String>> unitDigitStreamMap = numbers.stream()
                .collect(Collectors.groupingBy(number -> number % 10,   // number % 10 의 결과값이 같은것끼리 그룹으로 묶는다.
                        Collectors.mapping(number -> "unit digit is " + number, Collectors.toList()))); // number 는 문자열을 더한값이고, 리턴타입은 리스트이다.
        System.out.println(unitDigitStreamMap);

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
        // TODO: create a map from order status to the list of corresponding orders
        Map<OrderStatus, List<Order>> unitDigitOderStatus = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus));
        System.out.println(unitDigitOderStatus);

        // TODO: Order에 값들을 상태별로 그룹화 한 후에 그룹에 Amount 가격을 모두 더해준다.
        // TODO: 즉, 어떤 기준으로 그룹화를 한 후에 그 안에서 새로운 결과물을 만들어 냈다. 굉장하다. 코드가 간결하다.
        Map<OrderStatus, BigDecimal> orderStatusToSumOfAmountMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus,
                        Collectors.mapping(Order::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        System.out.println(orderStatusToSumOfAmountMap);
    }
}
