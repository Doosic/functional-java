package com.functionaljava.functionaljava.chapter8;

import com.functionaljava.functionaljava.chapter8.model.Order;
import com.functionaljava.functionaljava.chapter8.model.OrderLine;
import com.functionaljava.functionaljava.chapter8.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter8Section5 {

    public static void main(String[] args) {
        List<Integer> numberList = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.toList());
        System.out.println(numberList);

        // Set 이기에 순서가 뒤죽박죽이지만 중복값이 사라진다.
        Set<Integer> numberSet = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.toSet());
        System.out.println(numberSet);

        // abs 를 통해 절대값으로 변경
        // mapping(x, y) 앞의 인자값이 적용되고, 두번째 인자값의 타입으로 결과를 리턴
        List<Integer> absNumberList = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toList()));
        System.out.println(absNumberList);

        // 절대값이 되고나서 중복제거가 되어 3,4,5만 남았다.
        Set<Integer> absNumberSet = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toSet()));
        System.out.println(absNumberSet);

        int sum = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.reducing(0, (x,y) -> x + y));
        System.out.println(sum);
    }
}
