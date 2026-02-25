package com.mkcoats.user;

import java.util.UUID;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public User getUserById(UUID userId) {
        User[] users = userDAO.getUsers();
        for (int i = 0; i < users.length; i++) {
            User tempUser = users[i];
            if (tempUser != null && tempUser.getId().equals(userId)) {
                return tempUser;
            }
        }
        return null;
    }

    public User[] getAllUsers() {
        return userDAO.getUsers();
    }
}
