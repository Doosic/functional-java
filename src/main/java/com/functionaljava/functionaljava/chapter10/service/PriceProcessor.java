package com.functionaljava.functionaljava.chapter10.service;

import com.functionaljava.functionaljava.chapter10.model.Price;

@FunctionalInterface
public interface PriceProcessor {
    Price process(Price price);

    /*
        default 메서드는 andThen 이 호출됐을때 새로운 PriceProcessor 를 리턴해준다.
        1.구현되지 않은 메서드가 1개뿐이기에 @FunctionalInterface 과 람다를 사용해서 구현해줄 수 있다.
        2.인자값으로 price 메서드를 통해 자신먼저 process 를 해주고
        3.next 로 들어온 PriceProcessor 를 호출해주는 새로운 PriceProcessor 를 만들어 리턴해준다.

        완성된 코드의 흐름을 보니 어떤 구조인지 감이온다.
    */
    default PriceProcessor andThen(PriceProcessor next) {
        return price -> next.process(process(price));
    }
}
