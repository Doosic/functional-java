package com.functionaljava.functionaljava.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Chapter4Section2 {

    public static void main(String[] args) {
       Consumer<String> myStringConsumer = str ->
               System.out.println(str);
//       myStringConsumer.accept("hello~~");
//       myStringConsumer.accept("world!~~");

        List<Integer> integerInputs = Arrays.asList(4, 2, 3);
        Consumer<Integer> myIntegerProcessor = x ->
                System.out.println("Processing integer" + x);
        process(integerInputs, myIntegerProcessor);

        Consumer<Double> myDoubleProcessor = x ->
                System.out.println("Processing double" + x);
        List<Double> doubleInputs = Arrays.asList(1.1, 1.2, 1.3);
        process(doubleInputs, myDoubleProcessor);
    }


    public static <T> void process(List<T> inputs, Consumer<T> processor){
        for (T input : inputs){
            processor.accept(input);
        }
    }


}
