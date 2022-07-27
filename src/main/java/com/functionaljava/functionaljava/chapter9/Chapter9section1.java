package com.functionaljava.functionaljava.chapter9;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Chapter9section1 {

    public static void main(String[] args) {
        Supplier<String> supplier = getStringSupplier();
        System.out.println(supplier.get());

        BiFunction<Integer, Integer, Integer> add = (x,y) -> x + y;
        // x 를 먼저받고 이후 y 를 받아 x + y 라는 결과값을 내준다.
        Function<Integer, Function<Integer, Integer>> curriedAdd = x -> y -> x + y;

        // x 먼저 넣어준다.
        Function<Integer, Integer> addThree = curriedAdd.apply(3);
        // y 를 넣어 x + y 가 만들어진다.
        int result = addThree.apply(10);
        // result = 13
        System.out.println(result);
    }

    public static Supplier<String> getStringSupplier() {
        String hello = "Hello";
        Supplier<String> supplier = () -> {
            String world = "World";
            return hello + world;
        };
        return supplier;
    }
}
