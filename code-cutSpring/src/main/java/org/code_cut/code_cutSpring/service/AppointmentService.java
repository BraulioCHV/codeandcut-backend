package org.code_cut.code_cutSpring.service;


import org.code_cut.code_cutSpring.model.Appointment;
import org.code_cut.code_cutSpring.model.Employee;
import org.code_cut.code_cutSpring.model.Services;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointment();
    Appointment getAppointmentById(Long id);
    Appointment createAppointment(Appointment appointment);
    Appointment updateAppointmentById(Long id, Appointment appointment);
    void deleteAppointment(Long id);
    Appointment addServiceAndEmployee(Long id, Services serivce, Employee employee);


   // Appointment createAppointment(AppointmentRequest request);
    //void deleteAppointment(Long id);

}