package org.code_cut.code_cutSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.code_cut.code_cutSpring.model.Services;

public interface ServiceRepository extends JpaRepository<Services, Long> {
}