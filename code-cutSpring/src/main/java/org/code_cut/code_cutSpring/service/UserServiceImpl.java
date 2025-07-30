package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.dto.OrderRequest;
import org.code_cut.code_cutSpring.model.User;
import org.code_cut.code_cutSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + id + " no existe."));
    }

    @Override
    public User updateUser(int id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + id + " no existe."));

        if (userDetails.getName() != null) user.setName(userDetails.getName());
        if (userDetails.getLastName() != null) user.setLastName(userDetails.getLastName());
        if (userDetails.getEmail() != null) user.setEmail(userDetails.getEmail());
        if (userDetails.getPassword() != null) user.setPassword(userDetails.getPassword());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("El usuario con ID " + id + " no existe.");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User addOrders(int id, OrderRequest orderRequest) {
        return null;//vamos orlando tu puedes let's do it
    }
}
