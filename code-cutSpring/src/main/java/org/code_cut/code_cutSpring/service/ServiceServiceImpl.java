package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.Services;
import org.code_cut.code_cutSpring.repository.ServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<Services> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public Services createService(Services services) {
        return serviceRepository.save(services);
    }

    @Override
    public Services updateService(Long id, Services servicesDetails) {
        return serviceRepository.findById(id)
                .map(services -> {
                    services.setName(servicesDetails.getName());
                    services.setDescription(servicesDetails.getDescription());
                    services.setPrice(servicesDetails.getPrice());
                    return serviceRepository.save(services);
                }).orElseThrow(() -> new RuntimeException("Service not found"));
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}