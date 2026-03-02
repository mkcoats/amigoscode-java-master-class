package com.mkcoats.user;

import java.util.UUID;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserById(UUID userId) {
        for (User user : userDAO.getUsers()) {
            if (user != null && user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public User[] getAllUsers() {
        return userDAO.getUsers();
    }
}
