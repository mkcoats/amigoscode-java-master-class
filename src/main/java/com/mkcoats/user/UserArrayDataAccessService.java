package com.mkcoats.user;

import java.util.UUID;

public class UserArrayDataAccessService implements UserDAO{

    private static final User[] users;

    static {
        users = new User[]{
                new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "James"),
                new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Jamila"),
                new User(UUID.fromString("413df380-db93-4b2c-8ce3-91ab908b72ec"), "Alex"),
                new User(UUID.fromString("a39d72c3-a19b-430e-800a-c41b5172dc04"), "Bonnie"),
                new User(UUID.fromString("285e5250-be60-40bd-ba3b-377d4042aff6"), "Carl"),
                new User(UUID.fromString("1cbfbc66-47c4-4de6-8dba-60ae5352942d"), "Doug"),
                new User(UUID.fromString("0b5f3ba5-7b02-42d5-8e1b-ae68f318e2ee"), "Ezra"),
                new User(UUID.fromString("b6b522a7-9157-4049-993a-50b1a860633a"), "Frida"),
                new User(UUID.fromString("d4cfba3d-096f-4d03-a060-5c6f770bfefd"), "George"),
                new User(UUID.fromString("6b5f1276-8931-40b5-9e95-1f8b42230ee6"), "Hellen"),
                new User(UUID.fromString("c1651d0e-1e0b-402c-9ac7-64ada7fad5f1"), "Ike"),
                new User(UUID.fromString("27ee58e9-f1e6-4be8-9c7f-0f6db4c6c670"), "John")
        };
    }

    @Override
    public User[] getUsers() {
        return users;
    }
}
