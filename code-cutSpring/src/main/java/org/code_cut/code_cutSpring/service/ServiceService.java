package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.Services;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    List<Services> getAllServices();
    Optional<Services> getServiceById(Long id);
    Services createService(Services services);
    Services updateService(Long id, Services servicesDetails);
    void deleteService(Long id);
}