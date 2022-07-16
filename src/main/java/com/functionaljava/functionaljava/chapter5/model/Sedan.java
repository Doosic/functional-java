package com.functionaljava.functionaljava.chapter5.model;

public class Sedan extends Car{
    public Sedan(String name, String brand) {
        super(name, brand);
    }

    @Override
    public void drive() {
        System.out.printf("Driving a sedan %s from %s \n", name, brand);
    }
}
