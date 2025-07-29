package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.Service;
import org.code_cut.code_cutSpring.repository.ServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<Service> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public Service createService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Service updateService(Long id, Service serviceDetails) {
        return serviceRepository.findById(id)
                .map(service -> {
                    service.setName(serviceDetails.getName());
                    service.setDescription(serviceDetails.getDescription());
                    service.setPrice(serviceDetails.getPrice());
                    return serviceRepository.save(service);
                }).orElseThrow(() -> new RuntimeException("Service not found"));
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}