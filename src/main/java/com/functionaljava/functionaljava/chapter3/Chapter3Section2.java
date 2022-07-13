package com.functionaljava.functionaljava.chapter3;


import java.util.function.Function;

public class Chapter3Section2 {

    // 메서드 자체에는 이름이 없으며, myAdder 는 이름없는 함수를 담고있는 상자일뿐.
    public static void main(String[] args) {
        /*
            1.Function<Integer, Integer> myAdder = (Integer x) => 선언시 인풋 타입을 Integer 라고
              이미 알려줬기에 생략 가능 Function<Integer, Integer> myAdder = (x)

            2.(x) 의 역시 파라미터가 하나이기에 괄호 생략 가능 (x) => x

            3.뭔가를 처리하고 리턴하는것이 아닌 바로 값을 리턴해주기에 다음과 같이 변경가능하다.
              (중괄호 생략가능)
            전) Function<Integer, Integer> myAdder = x -> {
                    return x + 10;
                };

            후) Function<Integer, Integer> myAdder = x -> x + 10;

            참고)
                function 의 apply 는 결과값을 도출할때에 사용한다.

        */
        Function<Integer, Integer> myAdder = x -> x + 10;

        int result = myAdder.apply(5);
        System.out.println("result: "+result);
    }
}
