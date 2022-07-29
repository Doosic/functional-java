package com.functionaljava.functionaljava.chapter10;

import com.functionaljava.functionaljava.chapter10.model.Price;
import com.functionaljava.functionaljava.chapter10.model.User;
import com.functionaljava.functionaljava.chapter10.service.BasicPriceProcessor;
import com.functionaljava.functionaljava.chapter10.service.DiscountPriceProcessor;
import com.functionaljava.functionaljava.chapter10.service.PriceProcessor;
import com.functionaljava.functionaljava.chapter10.service.TaxPriceProcessor;

public class Chapter10section3 {

    public static void main(String[] args) {
        Price unprocessedPrice = new Price("Original Price");

        PriceProcessor basicPriceProcessor = new BasicPriceProcessor();
        PriceProcessor discountPriceProcessor = new DiscountPriceProcessor();
        PriceProcessor taxPriceProcessor = new TaxPriceProcessor();

        /*
            아무 기능도 없던 Processor 에 새로운 기능을 더해줬다.
            andThen 을 통하여 계속 기능을 더해줄 수 있다.
            뭔가 복잡한 로직들을 계속 더해야 할때에 이렇게 한다면 간단해질듯.
        */
        PriceProcessor decoratedPriceProcessor = basicPriceProcessor
                .andThen(discountPriceProcessor)
                .andThen(taxPriceProcessor);
        Price processedPrice = decoratedPriceProcessor.process(unprocessedPrice);
        System.out.println(processedPrice.getPrice());

        /*
            위와 같은 방식은 계속해서 기능을 덧붙일때에 클래스들을 생성해서 기능을 하나씩
            배정해줘야 한다. 그러나 함수를 사용하면 그렇게 하지 않아도됀다.
            그러나 문제점은 재사용이 불가능하다는 것이다. 클래스를 사용하면 기능의 재사용이 가능하다.
            필요에 따라 어느것이 필요한지에 따라 사용하도록 하자. 정말 공통적으로 사용할 수 있다면
            Util 로 빼서 클래스를 만들어두고 아니라면 즉시 정의해서 사용하는것도 괜찮겠다.
        */
        PriceProcessor decoratedPriceProcessor2 =  basicPriceProcessor
                .andThen(taxPriceProcessor)
                .andThen(price -> new Price(price.getPrice() + ", then apply another procedure"));
        Price processedPrice2 = decoratedPriceProcessor2.process(unprocessedPrice);
        System.out.println(processedPrice2.getPrice());
    }
}
