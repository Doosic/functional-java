package com.functionaljava.functionaljava.chapter9;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter9section2 {

    public static void main(String[] args) {
        // or 은 앞의 결과가 true 라면 뒤의 결과를 계산하지 않는다.
        // 만약 And 라도 앞의 결과가 false 라면 뒤의 결과를 계산하지 않는다.
        if (returnTrue() || returnFalse()) {
            System.out.println("true");
        }

        // false 가 있어도 if문 안의 내용이 실행된다.
        // 메서드 호출전에 모든 값들을 알고나서 메서드를 호출하기 때문에 가능한 일이다.
        if (or(returnTrue(), returnFalse())){
            System.out.println("true");
        }

        if (lazyOr(() -> returnTrue(), () -> returnFalse())) {
            System.out.println("lazy true");
        }

        System.out.println("============================================================================");

        /*
            아래 실행시 다음과 같은 결과가 나온다.
            Before collect
            peeking 3
            peeking 5
            peeking 8
            peeking 10
            After collect: [8, 10]

            왜 peeking 이 더 늦게 실행되었을까? (peek은 Stream 안을 잠시 들여다보는 함수)
            자바는 위에서부터 아래로 내려오는데...
            그 이유는 Stream 은 종결처리가 일어나기 전까지 모든 계산을 미루기 때문이다.
            collect 로 종결처리를 해주며 계산이 시작됐다.
         */
        Stream<Integer> integerStream = Stream.of(3, -2, 5, 8, -3, 10)
                .filter(x -> x > 0)
                .peek(x -> System.out.println("peeking " + x))
                .filter(x -> x % 2 == 0);
        System.out.println("Before collect");

        List<Integer> integers = integerStream.collect(Collectors.toList());
        System.out.println("After collect: " + integers);
    }
    public static boolean lazyOr(Supplier<Boolean> x, Supplier<Boolean> y) {
        return x.get() || y.get();
    }

    public static boolean or (boolean x, boolean y){
        return x || y;
    }

    public static boolean returnTrue() {
        System.out.println("Returning true");
        return true;
    }

    public static boolean returnFalse() {
        System.out.println("Returning false");
        return false;
    }
}
