package com.functionaljava.functionaljava.chapter10.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class User {
    private int id;
    private String name;
    private String emailAddress;
    private boolean isVerified;
    private LocalDateTime createdAt;
    private List<Integer> friendUserIds;
    /*
        1. User Class 안에있는 builder 메서드를 사용해 Builder 클래스를 생성한다.
        2. Builder 클래스의 값들을 원하는 값들로 초기화시켜주고 체이닝 방식을 사용한다.
        3. Builder 클래스 안의 User 타입의 build 라는 메서드를 통해
           User 클래스의 생성자를 호출하고, User 클래스의 생성자는 인자로 builder 를 받아
           변수들의 value 를 초기화시켜준다.
    */

    public User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.emailAddress = builder.emailAddress;
        this.isVerified = builder.isVerified;
        this.createdAt = builder.createdAt;
        this.friendUserIds = builder.friendUserIds;
    }

    public int getId() {
        return id;
    }
    /*
        Setter 는 체이닝을 위해서 this 를 넣어두었다.
    */

    public String getName() {
        return name;
    }


    public Optional<String> getEmailAddress() {
        return Optional.ofNullable(emailAddress);
    }


    public boolean isVerified() {
        return isVerified;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public List<Integer> getFriendUserIds() {
        return friendUserIds;
    }

    public static Builder builder(int id, String name){
        return new Builder(id, name);
    }

    public static class Builder {
        private int id;
        private String name;
        public String emailAddress;
        public boolean isVerified;
        public LocalDateTime createdAt;
        public List<Integer> friendUserIds = new ArrayList<>();

        private Builder(int id, String name){
            this.id = id;
            this.name = name;
        }
        // builder 를 Consumer 를 통해 함수형으로 재구현
        public Builder with(Consumer<Builder> consumer){
            consumer.accept(this);
            return this;
        }

        public User build() {
            return new User(this);
        }
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", isVerified=" + isVerified +
                ", createdAt=" + createdAt +
                ", friendUserIds=" + friendUserIds +
                '}';
    }
}
