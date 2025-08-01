package org.code_cut.code_cutSpring.service;



import org.code_cut.code_cutSpring.model.Appointment;
import org.code_cut.code_cutSpring.model.Employee;
import org.code_cut.code_cutSpring.model.Services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface AppointmentService {

    Appointment createAppointment(Appointment appointment);

    Optional<Appointment> updateAppointmentById(Integer id, Appointment appointment);

    void deleteAppointment(Integer id);

    Appointment addServiceAndEmployee(Integer id, Services service, Employee employee);

    Optional<Appointment> getAppointmentById(Integer id);

    List<Appointment> getAllAppointments();
}