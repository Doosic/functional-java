package com.functionaljava.functionaljava.chapter9.priceprocessor;

import com.functionaljava.functionaljava.chapter9.model.Order;
import com.functionaljava.functionaljava.chapter9.model.OrderLine;

import java.math.BigDecimal;
import java.util.function.Function;

public class OrderLineAggregationPriceProcessor implements Function<Order, Order> {

    // OrderLine 의 가격의 합을 return 해주는 프로세스를 만듬.
    @Override
    public Order apply(Order order) {
        return order.setAmount(order.getOrderLines().stream()
                .map(OrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
