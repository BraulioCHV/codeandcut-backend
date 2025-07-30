package org.code_cut.code_cutSpring.repository;

import org.code_cut.code_cutSpring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}


