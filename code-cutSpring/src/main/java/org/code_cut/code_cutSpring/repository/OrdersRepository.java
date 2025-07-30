package org.code_cut.code_cutSpring.repository;

import org.code_cut.code_cutSpring.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
