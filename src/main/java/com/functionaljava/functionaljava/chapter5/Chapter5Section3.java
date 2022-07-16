package com.functionaljava.functionaljava.chapter5;

import com.functionaljava.functionaljava.chapter5.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class Chapter5Section3 {

    public static void main(String[] args) {
        Map<String, BiFunction<String, String, Car>> carTypeToConstructor = new HashMap<>();
        carTypeToConstructor.put("sedan", Sedan::new);
        carTypeToConstructor.put("suv", Suv::new);
        carTypeToConstructor.put("van", Van::new);

        User user = new User(1, "Alice");
        BiFunction<Integer, String, User> userCreator = User::new;
        User charlie = userCreator.apply(3, "Charlie");
        System.out.println(charlie);

        String[][] inputs = new String[][] {
                {"sedan", "Sonata", "Hyundai"},
                {"van", "Sienna", "Toyota"},
                {"sedan", "Model S", "Tesla"},
                {"suv", "Sorento", "KIA"}
        };

        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < inputs.length; ++i){
            String[] input = inputs[i];
            String carType = input[0];
            String name = input[1];
            String brand = input[2];

            cars.add(carTypeToConstructor.get(carType).apply(name, brand));
        }

        for (Car car : cars){
            car.drive();
        }
    }

}
