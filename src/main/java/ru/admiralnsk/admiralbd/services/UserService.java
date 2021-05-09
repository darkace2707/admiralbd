package ru.admiralnsk.admiralbd.services;

import ru.admiralnsk.admiralbd.models.User;

import java.util.List;

public interface UserService {
    User findUserById(Long id);

    List<User> findAll();

    void putUser(User user);

    void updateUser(User user);

    void deleteUserById(Long id);
}
