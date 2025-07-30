package org.code_cut.code_cutSpring.controller;

import lombok.AllArgsConstructor;
import org.code_cut.code_cutSpring.model.Service;
import org.code_cut.code_cutSpring.service.ServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/services")
@AllArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping // http://localhost:8080/api/services
    public List<Service> getAllServices() {
        return this.serviceService.getAllServices();
    }

    @GetMapping(path = "{id}") // http://localhost:8080/api/services/{id}
    public Service getServiceById(@PathVariable("id") Long id) {
        return this.serviceService.getServiceById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con id: " + id));
    }

    @PostMapping // http://localhost:8080/api/services
    public Service createService(@RequestBody Service service) {
        return this.serviceService.createService(service);
    }

    @PutMapping(path = "{id}") // http://localhost:8080/api/services/{id}
    public Service updateService(@PathVariable("id") Long id, @RequestBody Service serviceDetails) {
        return this.serviceService.updateService(id, serviceDetails);
    }

    @DeleteMapping(path = "{id}") // http://localhost:8080/api/services/{id}
    public void deleteService(@PathVariable("id") Long id) {
        this.serviceService.deleteService(id);
    }
}
