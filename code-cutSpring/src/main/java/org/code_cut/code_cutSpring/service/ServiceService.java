package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    List<Service> getAllServices();
    Optional<Service> getServiceById(Long id);
    Service createService(Service service);
    Service updateService(Long id, Service serviceDetails);
    void deleteService(Long id);
}