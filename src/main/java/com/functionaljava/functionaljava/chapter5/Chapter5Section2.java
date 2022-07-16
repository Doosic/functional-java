package com.functionaljava.functionaljava.chapter5;

import java.util.function.BiPredicate;
import java.util.function.Function;

public class Chapter5Section2 {

    public static void main(String[] args) {
        Function<String, Integer> strLength = String::length;
        int length = strLength.apply("hello world");
        System.out.println(length);

        BiPredicate<String, String> strEquals = String::equals;
        boolean test = strEquals.test("hello", "Not Hello");
        System.out.println(test);
    }
}
