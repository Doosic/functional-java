package com.functionaljava.functionaljava.chapter9;

import com.functionaljava.functionaljava.chapter9.model.Order;
import com.functionaljava.functionaljava.chapter9.model.OrderLine;
import com.functionaljava.functionaljava.chapter9.priceprocessor.OrderLineAggregationPriceProcessor;
import com.functionaljava.functionaljava.chapter9.priceprocessor.TaxPriceProcessor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.function.Function.identity;

public class Chapter9section3 {

    public static void main(String[] args) {
        Function<Integer, Integer> multiplyByTwo = x -> 2 * x;
        Function<Integer, Integer> addThen = x -> x + 10;

        // 2개의 함수를 합쳤다. 결과값 16
        Function<Integer, Integer> composedFunction = multiplyByTwo.andThen(addThen);
        System.out.println(composedFunction.apply(3));

        Order unprocessedOrder = new Order()
                .setId(1001)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))
                ));

        List<Function<Order, Order>> priceProcessors = getPriceProcessors(unprocessedOrder);

        // 오퍼레이터 사용전 .reduce(identity(), (priceProcessors1, priceProcessors2) -> priceProcessors1.andThen(priceProcessors2));
        // 사용후 .reduce(identity(), Function::andThen);
        Function<Order, Order> mergedPriceProcessors = priceProcessors.stream()
                .reduce(identity(), Function::andThen);

        Order processedOrder = mergedPriceProcessors.apply(unprocessedOrder);
        System.out.println(processedOrder);
    }

    public static List<Function<Order, Order>> getPriceProcessors(Order order){
        return Arrays.asList(new OrderLineAggregationPriceProcessor(),
                new TaxPriceProcessor(new BigDecimal("9.375")));
    }
}
