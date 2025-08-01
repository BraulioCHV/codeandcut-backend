package org.code_cut.code_cutSpring.repository;


import org.code_cut.code_cutSpring.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}