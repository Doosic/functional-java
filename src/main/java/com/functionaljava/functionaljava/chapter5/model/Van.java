package com.functionaljava.functionaljava.chapter5.model;

public class Van extends Car{
    public Van(String name, String brand) {
        super(name, brand);
    }

    @Override
    public void drive() {
        System.out.printf("Driving van %s from %s \n", name, brand);
    }
}
