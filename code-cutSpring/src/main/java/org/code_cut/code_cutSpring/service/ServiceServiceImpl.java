package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.Service;
import org.code_cut.code_cutSpring.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service as ServiceAnnotation;

import java.util.List;
import java.util.Optional;

@ServiceAnnotation
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Service getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El servicio con el id " + id + " no existe"));
    }

    @Override
    public Service addService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Service updateService(Long id, Service serviceUpdate) {
        Optional<Service> optionalService = serviceRepository.findById(id);
        if (optionalService.isEmpty()) {
            throw new IllegalArgumentException("El servicio con el id " + id + " no existe");
        }

        Service originalService = optionalService.get();

        if (serviceUpdate.getName() != null) originalService.setName(serviceUpdate.getName());
        if (serviceUpdate.getDescription() != null) originalService.setDescription(serviceUpdate.getDescription());
        if (serviceUpdate.getPrice() != null && serviceUpdate.getPrice() != 0) {
            originalService.setPrice(serviceUpdate.getPrice());
        }

        return serviceRepository.save(originalService);
    }

    @Override
    public Service deleteServiceById(Long id) {
        if (!serviceRepository.existsById(id)) return null;

        Service temp = serviceRepository.findById(id).get();
        serviceRepository.deleteById(id);
        return temp;
    }
}
