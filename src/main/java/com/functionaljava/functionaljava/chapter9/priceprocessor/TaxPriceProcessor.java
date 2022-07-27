package com.functionaljava.functionaljava.chapter9.priceprocessor;

import com.functionaljava.functionaljava.chapter9.model.Order;

import java.math.BigDecimal;
import java.util.function.Function;

public class TaxPriceProcessor implements Function<Order, Order> {

    private final BigDecimal taxRate;

    public TaxPriceProcessor(BigDecimal taxRate){
        this.taxRate = taxRate;
    }

    // 세율을 계산하는 프로세스
    @Override
    public Order apply(Order order) {
        return order.setAmount(order.getAmount()
                .multiply((taxRate.divide(new BigDecimal(100)).add(BigDecimal.ONE))));
    }
}
