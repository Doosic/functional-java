package com.functionaljava.functionaljava.chapter6;

import com.functionaljava.functionaljava.chapter6.model.Order;
import com.functionaljava.functionaljava.chapter6.model.OrderLine;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.functionaljava.functionaljava.chapter6.model.Order.OrderStatus.*;
import static com.functionaljava.functionaljava.chapter6.model.OrderLine.OrderLineType.DISCOUNT;
import static com.functionaljava.functionaljava.chapter6.model.OrderLine.OrderLineType.PURCHASE;

public class Chapter6Section7 {

    public static void main(String[] args) {
        String[][] cities = new String[][]{
                {"Seoul", "Busan"},
                {"San Francisco", "New York"},
                {"Madrid", "Barcelona"},
        };
     Stream<String[]> cityStream =  Arrays.stream(cities);
     Stream<Stream<String>> cityStreamStream = cityStream.map(x -> Arrays.stream(x));
     List<Stream<String>> cityStreamList = cityStreamStream.collect(Collectors.toList());
     System.out.println(cityStreamList);

     Stream<String[]> cityStream2 = Arrays.stream(cities);
     Stream<String> flattenedCityStream = cityStream2.flatMap(x -> Arrays.stream(x));
     List<String> flattenedCityList = flattenedCityStream.collect(Collectors.toList());
     System.out.println(flattenedCityList);

     Order order1 = new Order()
             .setId(1001)
             .setOrderLines(Arrays.asList(
                     new OrderLine()
                             .setId(10001)
                             .setType(PURCHASE)
                             .setAmount(BigDecimal.valueOf(5000)),
                     new OrderLine()
                             .setId(10002)
                             .setType(PURCHASE)
                             .setAmount(BigDecimal.valueOf(4000))
             ));
     Order order2 = new Order()
             .setId(1002)
             .setOrderLines(Arrays.asList(
                     new OrderLine()
                             .setId(10003)
                             .setType(PURCHASE)
                             .setAmount(BigDecimal.valueOf(2000)),
                     new OrderLine()
                             .setId(10004)
                             .setType(DISCOUNT)
                             .setAmount(BigDecimal.valueOf(-1000))
             ));
     Order order3 = new Order()
             .setId(1003)
             .setOrderLines(Arrays.asList(
                     new OrderLine()
                             .setId(10005)
                             .setType(PURCHASE)
                             .setAmount(BigDecimal.valueOf(2000))
             ));
     List<Order> orders = Arrays.asList(order1, order2, order3);

     // 유저의 장바구니가 여러개가 있고, 그 장바구니를 합치고 싶을때가 있다.
     List<OrderLine> mergedOrderLines = orders.stream() // Stream<Order>
             .map(Order::getOrderLines)                 // Stream<List<OrderLine>>
             .flatMap(List::stream)                     // Stream<OrderLine>
             .collect(Collectors.toList());
     System.out.println(mergedOrderLines);

    }
}
