package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.dto.AppointmentRequest;
import org.code_cut.code_cutSpring.dto.OrderRequest;
import org.code_cut.code_cutSpring.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    User updateUser(int id, User user);
    void deleteUser(int id);
    User addOrders(int id, OrderRequest orderRequest);
    User addAppointment(int id, AppointmentRequest appointmentRequest);
    Optional<User> findByEmail(String email);
}

