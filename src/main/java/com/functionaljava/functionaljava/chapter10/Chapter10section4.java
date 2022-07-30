package com.functionaljava.functionaljava.chapter10;

import com.functionaljava.functionaljava.chapter10.model.User;
import com.functionaljava.functionaljava.chapter10.service.EmailProvider;
import com.functionaljava.functionaljava.chapter10.service.EmailSender;
import com.functionaljava.functionaljava.chapter10.service.MakeMoreFriendsEmailProvider;
import com.functionaljava.functionaljava.chapter10.service.VerifyYourEmailAddressEmailProvider;

import java.util.Arrays;
import java.util.List;

public class Chapter10section4 {

    public static void main(String[] args) {
        User user1 = User.builder(1, "Alice")
                .with(builder -> {
                    builder.emailAddress = "alice@naver.com";
                    builder.isVerified = false;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
                }).build();

        User user2 = User.builder(2, "Bob")
                .with(builder -> {
                    builder.emailAddress = "bob@naver.com";
                    builder.isVerified = true;
                    builder.friendUserIds = Arrays.asList(212, 213, 214);
                }).build();

        User user3 = User.builder(3, "Charlie")
                .with(builder -> {
                    builder.emailAddress = "charlie@naver.com";
                    builder.isVerified = true;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212);
                }).build();
        List<User> users = Arrays.asList(user1, user2, user3);

        EmailSender emailSender = new EmailSender();
        EmailProvider verifyYourEmailAddressEmailProvider = new VerifyYourEmailAddressEmailProvider();
        EmailProvider makeMoreFriendsEmailProvider = new MakeMoreFriendsEmailProvider();

        emailSender.setEmailProvider(verifyYourEmailAddressEmailProvider);
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailSender::sendEmail);

        emailSender.setEmailProvider(makeMoreFriendsEmailProvider);
        users.stream()
                .filter(User::isVerified)
                .filter(user -> user.getFriendUserIds().size() <= 5)
                .forEach(emailSender::sendEmail);

        // 즉시 전략 생성 가능.
        emailSender.setEmailProvider(user -> "'Play With Friends' email for " + user.getName());
        users.stream()
                .filter(User::isVerified)
                .filter(user -> user.getFriendUserIds().size() > 5)
                .forEach(emailSender::sendEmail);
    }
}
