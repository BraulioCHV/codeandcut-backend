package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.Service;

import java.util.List;

public interface ServiceService {
    List<Service> getAllServices();
    Service getServiceById(Long id);
    Service addService(Service service);
    Service updateService(Long id, Service serviceUpdate);
    Service deleteServiceById(Long id);
}
