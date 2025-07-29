package org.code_cut.code_cutSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.code_cut.code_cutSpring.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}