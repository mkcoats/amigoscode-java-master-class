package com.mkcoats.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserArrayFileDataService implements UserDAO {
    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("src/main/java/com/mkcoats/users.csv"));
            while(scanner.hasNext()) {
                // "UUID as String, Name as String"
                String[] values = scanner.nextLine().split(",");
                users.add(new User(UUID.fromString(values[0]), values[1]));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return users;
    }
}
