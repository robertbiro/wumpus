package org.example.repository;

import org.example.entities.User;

public interface UserRepository {

    void saveUser(User user);
    User getUserById(Long userId);
    boolean isUserNameExists(String userName);

    boolean isPasswordCorrect(String userName, String password);
}
