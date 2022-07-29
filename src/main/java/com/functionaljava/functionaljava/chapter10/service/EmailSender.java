package com.functionaljava.functionaljava.chapter10.service;

import com.functionaljava.functionaljava.chapter10.model.User;

public class EmailSender {
    private EmailProvider emailProvider;

    public EmailSender setEmailProvider(EmailProvider emailProvider){
        this.emailProvider = emailProvider;
        return this;
    }

    // 실시간으로 전략을 교체하며 이메일을 다룰 수 있는 클래스이다.
    public void sendEmail(User user){
        String email = emailProvider.getEmail(user);
        System.out.println("Sending " + email);
    }
}
