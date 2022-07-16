package com.functionaljava.functionaljava.chapter5;

import com.functionaljava.functionaljava.chapter5.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class Chapter5Section2 {

    public static void main(String[] args) {
        Function<String, Integer> strLength = String::length;
        int length = strLength.apply("hello world");
        System.out.println(length);

        BiPredicate<String, String> strEquals = String::equals;
        boolean test = strEquals.test("hello", "Not Hello");
        System.out.println(test);

        List<User> users = new ArrayList<>();
        users.add(new User(3, "Alice"));
        users.add(new User(7, "Charlie"));
        users.add(new User(4, "Bob"));

        printUserField(users, User::getId);
    }

    public static void printUserField(List<User> users, Function<User, Object> getter){
        for (User user : users) {
            System.out.println(user);
        }
    }
}
