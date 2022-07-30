package com.functionaljava.functionaljava.chapter10;

import com.functionaljava.functionaljava.chapter10.model.User;
import com.functionaljava.functionaljava.chapter10.service.InternalUserService;
import com.functionaljava.functionaljava.chapter10.service.UserService;
import com.functionaljava.functionaljava.chapter10.service.UserServiceInFunctionalWay;

import java.util.Arrays;
import java.util.List;

public class Chapter10section5 {

    public static void main(String[] args) {
        User alice = User.builder(1, "Alice")
                .with(builder -> {
                    builder.emailAddress = "alice@naver.com";
                    builder.isVerified = false;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
                }).build();

        UserService userService = new UserService();
        InternalUserService internalUserService = new InternalUserService();

        userService.createUser(alice);
        internalUserService.createUser(alice);

        UserServiceInFunctionalWay userServiceInFunctionalWay = new UserServiceInFunctionalWay(
                user -> {
                    System.out.println("Validating user " + user.getName());
                    return user.getName() != null && user.getEmailAddress().isPresent();
                },
                user -> {
                    System.out.println("Writing user " + user.getName() + " to DB");
                });
        userServiceInFunctionalWay.createUser(alice);

    }
}
