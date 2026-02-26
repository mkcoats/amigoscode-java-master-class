package com.mkcoats.user;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class UserArrayFileDataService implements UserDAO {
    @Override
    public User[] getUsers() {
        User[] users = new User[12];
        try {
            Scanner scanner = new Scanner(new File("src/main/java/com/mkcoats/users.csv"));
            int index = 0;
            while(scanner.hasNext()) {
                // "UUID as String, Name as String"
                String[] values = scanner.nextLine().split(",");
                users[index] = new User(UUID.fromString(values[0]), values[1]);
                index++;
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return users;
    }
}
