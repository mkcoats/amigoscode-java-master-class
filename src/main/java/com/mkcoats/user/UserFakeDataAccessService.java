package com.mkcoats.user;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserFakeDataAccessService implements UserDAO {

    private final int USER_COUNT = 20;

    @Override
    public List<User> getUsers() {
        Faker faker = new Faker();
        List<User> users = new ArrayList<>();
        for(int i = 0; i < USER_COUNT; i++) {
            users.add(new User(UUID.randomUUID(), faker.name().firstName()));
        }
        return users;
    }
}
