package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.Appointment;
import org.code_cut.code_cutSpring.model.Employee;
import org.code_cut.code_cutSpring.model.Services;
import org.code_cut.code_cutSpring.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Métoodo agregado; bonsulta en la base de datos todas las citas y devuelve una lista con ellas.
    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Métoodo agregado;  sirve para buscar una cita específica por su ID en tu base de datos y devolverla si existe
    @Override
    public Optional<Appointment> getAppointmentById(Integer id) {
        return appointmentRepository.findById(id);
    }

    //Guarda la cita actualizada en la base de datos.
    @Override
    public Optional<Appointment> updateAppointmentById(Integer id, Appointment appointmentUpdated) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            Appointment existingAppointment = optionalAppointment.get();

            if (appointmentUpdated.getDateHour() != null) {
                existingAppointment.setDateHour(appointmentUpdated.getDateHour());
            }
            if (appointmentUpdated.getStatus() != null) {
                existingAppointment.setStatus(appointmentUpdated.getStatus());
            }
            // Puedes añadir lógica para actualizar el usuario si es necesario
            if (appointmentUpdated.getUser() != null) {
                existingAppointment.setUser(appointmentUpdated.getUser());
            }

            return Optional.of(appointmentRepository.save(existingAppointment));
        }
        return Optional.empty(); // Retorna Optional vacío
    }

    @Override
    public void deleteAppointment(Integer id) {
        // Usa `existsById` para verificar antes de eliminar
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
        }
    }

    // Lógica para agregar servicio y empleado
    @Override

    public Appointment addServiceAndEmployee(Integer id, Services service, Employee employee) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            Appointment existingAppointment = optionalAppointment.get();

            // Si el servicio y/o el empleado no son nulos, actualiza la cita.
            // Asume que la entidad Appointment tiene campos para Employee y Services.
            if (service != null) {
                // existingAppointment.setServices(service);
            }
            if (employee != null) {
                // existingAppointment.setEmployee(employee);
            }

            // Guarda la cita actualizada.
            return appointmentRepository.save(existingAppointment);
        }
        return null; // O lanza una excepción si la cita no se encuentra
    }
}