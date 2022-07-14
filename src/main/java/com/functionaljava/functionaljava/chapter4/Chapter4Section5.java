
package com.functionaljava.functionaljava.chapter4;

import com.functionaljava.functionaljava.chapter4.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Chapter4Section5 {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(3, "Alice"));
        users.add(new User(1, "Charlie"));
        users.add(new User(5, "Bob"));

        Comparator<User> idComparator = (u1, u2) -> u1.getId() - u2.getId();

        Collections.sort(users, idComparator);
        System.out.println(users);

        // 내 입맛대로 sort 를 할 수 있다. 이름기준 또는 id 기준...~ 여러가지로
        Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));
        System.out.println(users);
    }
}
