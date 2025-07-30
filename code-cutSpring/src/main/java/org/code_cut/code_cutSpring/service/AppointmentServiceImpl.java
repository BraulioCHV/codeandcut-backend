package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.Appointment;
import org.code_cut.code_cutSpring.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    // Inyección de dependencias
    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    // Obtiene una cita por ID
    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    // Actualiza Cita con Id
    @Override
    public Appointment updateAppointmentById(Long id, Appointment appointmentUpdated) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            Appointment existingAppointment = optionalAppointment.get();

            // Actualiza solo los campos que llegan no nulos
            if (appointmentUpdated.getDateHour() != null) {
                existingAppointment.setDateHour(appointmentUpdated.getDateHour());
            }
            if (appointmentUpdated.getStatus() != null) {
                existingAppointment.setStatus(appointmentUpdated.getStatus());
            }

            return appointmentRepository.save(existingAppointment);
        }
        return null;
    }
    // Elimina una cita por ID
    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
