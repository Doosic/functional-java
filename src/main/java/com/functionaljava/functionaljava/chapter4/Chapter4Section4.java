
package com.functionaljava.functionaljava.chapter4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Chapter4Section4 {

    public static void main(String[] args) {
        // 양수인지 음수인지 테스트해주는 코드 (0보다 크냐 작냐)
        Predicate<Integer> isPositive = x -> x > 0;
        List<Integer> inputs = Arrays.asList(10, -5, 4, 3, -2, 0);

        // 결과의 true 를 반환해준다.
        System.out.println("Positive number: "
                + filter(inputs, isPositive));

        // negate(): return (t) -> !test(t); 결과의 반대를 반환해준다.
        System.out.println("Non-positive number: "
                + filter(inputs, isPositive.negate()));

        // or(): 0보다 큰것들만 출력이되야하나 or 로 조건을 추가하였다.
        System.out.println("Non-negative number: "
                + filter(inputs, isPositive.or(x -> x == 0)));

        // and(): 조건 추가 양수이면서 짝수인것만 출력
        System.out.println("Positive even numbers: "
                + filter(inputs, isPositive.and(x -> x % 2 == 0)));
    }

    /*
        filter 메소드는 리스트를 받아 양수인지 음수인지 체크하여
        조건에 부합한 애들만 담아 출력해주는 함수이다.
    */
    public static <T> List<T> filter(List<T> inputs, Predicate<T> condition){
        List<T> output = new ArrayList<>();
        for (T input : inputs) {
            if (condition.test(input)){
                output.add(input);
            }
        }
        return output;
    }
}
