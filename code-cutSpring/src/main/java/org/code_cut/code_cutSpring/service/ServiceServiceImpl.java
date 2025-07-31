package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.dto.AppointmentRequest;
import org.code_cut.code_cutSpring.dto.OrderRequest;
import org.code_cut.code_cutSpring.model.Appointment;
import org.code_cut.code_cutSpring.model.Orders;
import org.code_cut.code_cutSpring.model.User;
import org.code_cut.code_cutSpring.repository.AppointmentRepository;
import org.code_cut.code_cutSpring.repository.OrdersRepository;
import org.code_cut.code_cutSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public User createUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }
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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + id + " no existe."));

        Orders order = new Orders();
        order.setAddress(orderRequest.getAddress());
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setUser(user);

        ordersRepository.save(order);

        user.getOrders().add(order);
        return userRepository.save(user);
    }

    @Override
    public User addAppointment(int id, AppointmentRequest appointmentRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + id + " no existe."));

        Appointment appointment = new Appointment();
        appointment.setDateHour(appointmentRequest.getDateHour());
        appointment.setStatus(appointmentRequest.getStatus());
        appointment.setUser(user);

        appointmentRepository.save(appointment);

        user.getAppointments().add(appointment);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
