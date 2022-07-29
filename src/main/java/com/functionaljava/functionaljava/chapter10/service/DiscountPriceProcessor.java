package com.functionaljava.functionaljava.chapter10.service;

import com.functionaljava.functionaljava.chapter10.model.Price;

public class DiscountPriceProcessor implements PriceProcessor{
    @Override
    public Price process(Price price) {
        return new Price(price.getPrice() + ", then applied discount");
    }
}
