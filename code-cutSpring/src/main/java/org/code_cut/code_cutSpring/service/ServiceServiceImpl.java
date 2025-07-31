package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.Appointment;
import org.code_cut.code_cutSpring.model.Services;
import org.code_cut.code_cutSpring.repository.AppointmentRepository;
import org.code_cut.code_cutSpring.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServicesRepository servicesRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public ServiceServiceImpl(ServicesRepository servicesRepository, AppointmentRepository appointmentRepository) {
        this.servicesRepository = servicesRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    @Override
    public Optional<Services> getServiceById(Long id) {
        return servicesRepository.findById(id);
    }

    @Override
    public Services createService(Services services) {
        // Valida que la cita a la que se quiere asociar el servicio exista
        if (services.getAppointment() != null) {
            Long appointmentId = services.getAppointment().getIdAppointment();
            Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new IllegalArgumentException("La cita con ID " + appointmentId + " no existe"));
            services.setAppointment(appointment); // Asociar la cita existente
        }
        return servicesRepository.save(services);
    }

    @Override
    public Services updateService(Long id, Services servicesDetails) {
        Services existingService = servicesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El servicio con ID " + id + " no existe."));

        if (servicesDetails.getName() != null) existingService.setName(servicesDetails.getName());
        if (servicesDetails.getDescription() != null) existingService.setDescription(servicesDetails.getDescription());
        if (servicesDetails.getPrice() != null) existingService.setPrice(servicesDetails.getPrice());

        if (servicesDetails.getAppointment() != null) {
            Long newAppointmentId = servicesDetails.getAppointment().getIdAppointment();
            Appointment appointment = appointmentRepository.findById(newAppointmentId)
                    .orElseThrow(() -> new IllegalArgumentException("La cita con ID " + newAppointmentId + " no existe"));
            existingService.setAppointment(appointment);
        }

        return servicesRepository.save(existingService);
    }

    @Override
    public void deleteService(Long id) {
        if (!servicesRepository.existsById(id)) {
            throw new IllegalArgumentException("El servicio con ID " + id + " no existe.");
        }
        servicesRepository.deleteById(id);
    }
}
