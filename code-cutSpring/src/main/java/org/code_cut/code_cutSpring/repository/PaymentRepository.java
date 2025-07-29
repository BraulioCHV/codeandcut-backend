package org.code_cut.code_cutSpring.repository;

import org.code_cut.code_cutSpring.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
