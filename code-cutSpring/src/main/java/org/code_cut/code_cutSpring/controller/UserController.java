package org.code_cut.code_cutSpring.controller;

import org.code_cut.code_cutSpring.dto.AppointmentRequest;
import org.code_cut.code_cutSpring.dto.OrderRequest;
import org.code_cut.code_cutSpring.dto.UserRequest;
import org.code_cut.code_cutSpring.model.User;
import org.code_cut.code_cutSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UserRequest userRequest) {
        User updatedUser = new User();
        updatedUser.setName(userRequest.getName());
        updatedUser.setLastName(userRequest.getLastName());
        updatedUser.setEmail(userRequest.getEmail());
        updatedUser.setPassword(userRequest.getPassword());

        try {
            return ResponseEntity.ok(userService.updateUser(id, updatedUser));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/orders")
    public ResponseEntity<User> addOrder(@PathVariable int id, @RequestBody OrderRequest orderRequest) {
        User user = userService.addOrders(id, orderRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{id}/appointments")
    public ResponseEntity<User> addAppointment(@PathVariable int id, @RequestBody AppointmentRequest appointmentRequest) {
        User user = userService.addAppointment(id, appointmentRequest);
        return ResponseEntity.ok(user);
    }

    // Nuevo endpoint para login
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userOpt.get();
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(user);
    }
}
