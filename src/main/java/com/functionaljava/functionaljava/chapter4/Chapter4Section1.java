package com.functionaljava.functionaljava.chapter4;

import java.util.function.Supplier;

public class Chapter4Section1 {

    public static void main(String[] args) {
       Supplier<String> myStringSupplier = () -> {
           return "hello world!";
       };
        System.out.println(myStringSupplier.get());

        Supplier<Double> myRandomDoubleSupplier = () -> Math.random();
        printRandomDouble(myRandomDoubleSupplier, 5);
    }

    public static void printRandomDouble (Supplier<Double> randomSupplier,
                                          int count){
        for (int i = 0; i < count; i ++){
            System.out.println(randomSupplier.get());
        }
    }
}
