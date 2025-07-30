package org.code_cut.code_cutSpring.controller;

import org.code_cut.code_cutSpring.model.Appointment;
import org.code_cut.code_cutSpring.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Crea una una cita
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    // Obtiene todas las citas
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointment();
    }

    //Obtiene una cita por ID
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    // Actualiza una cita por ID
    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        return appointmentService.updateAppointmentById(id, appointment);
    }

    // Elimina una cita por ID
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}