package com.functionaljava.functionaljava.chapter6;

import com.functionaljava.functionaljava.chapter6.model.Order;
import com.functionaljava.functionaljava.chapter6.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.functionaljava.functionaljava.chapter6.model.Order.OrderStatus.*;

public class Chapter6Section6 {

    public static void main(String[] args) {
     List<Integer> numbers = Arrays.asList(3, -5, 4, -5, 2, 3);
     List<Integer> distinctNumbers = numbers.stream()
             .distinct()
             .collect(Collectors.toList());
      System.out.println(distinctNumbers);

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

      // TODO: created a sorted list of unique CreatedByUserIds from the orders
      List<Long> sortedAndUniqueByUserIds = orders.stream()
              .map(Order::getCreatedByUserId)
              .distinct()
              .sorted()
              .collect(Collectors.toList());
      System.out.println(sortedAndUniqueByUserIds);
    }
}
