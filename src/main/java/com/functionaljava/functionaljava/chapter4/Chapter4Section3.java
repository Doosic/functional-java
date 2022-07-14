package com.functionaljava.functionaljava.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.function. BiConsumer;

public class Chapter4Section3 {

    public static void main(String[] args) {
        // 프로세싱 과정중 인풋의 위치가 중요하고, 인덱스로 매개변수로 받아야 한다는 가정으로 만든다.
        BiConsumer<Integer, Double> myDoubleProcessor =
                (index, input) ->
                        System.out.println("Processing " + input + " at index " + index);
        List<Double> inputs = Arrays.asList(1.1, 2.2, 3.3);
        process(inputs, myDoubleProcessor);
    }

    public static <T> void process(List<T> inputs, BiConsumer<Integer, T> processor){
        for (int i = 0; i < inputs.size(); i++){
            processor.accept(i, inputs.get(i));
        }
    }
}
