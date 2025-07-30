package org.code_cut.code_cutSpring.service;


import org.code_cut.code_cutSpring.model.Appointment;
import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointment();
    Appointment getAppointmentById(Long id);
    Appointment createAppointment(Appointment appointment);
    Appointment updateAppointmentById(Long id, Appointment appointment);
    void deleteAppointment(Long id);


   // Appointment createAppointment(AppointmentRequest request);
    //void deleteAppointment(Long id);

}