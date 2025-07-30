package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    User updateUser(int id, User user);
    void deleteUser(int id);
}
