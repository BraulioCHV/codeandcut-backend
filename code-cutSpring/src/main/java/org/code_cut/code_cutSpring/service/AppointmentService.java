package org.code_cut.code_cutSpring.service;



import org.code_cut.code_cutSpring.model.Appointment;
import org.code_cut.code_cutSpring.model.Employee;
import org.code_cut.code_cutSpring.model.Services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(Appointment appointment);
    Appointment updateAppointmentById(Long id, Appointment appointment);
    void deleteAppointment(Long id);
    Appointment addServiceAndEmployee(Long id, Services service, Employee employee);





    // Appointment createAppointment(AppointmentRequest request);
    //void deleteAppointment(Long id);
}