package com.functionaljava.functionaljava.chapter5.model;

public class Suv extends Car{
    public Suv(String name, String brand) {
        super(name, brand);
    }

    @Override
    public void drive() {
        System.out.printf("Driving suc %s from %s \n", name, brand);
    }
}
