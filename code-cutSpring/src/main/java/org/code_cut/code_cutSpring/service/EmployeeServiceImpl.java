package org.code_cut.code_cutSpring.service;

import lombok.AllArgsConstructor;
import org.code_cut.code_cutSpring.model.Employee;
import org.code_cut.code_cutSpring.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: " + id));
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee employeeUpdate) {
        Employee existing = getEmployeeById(id);
        existing.setName(employeeUpdate.getName());
        existing.setLastname(employeeUpdate.getLastname());
        existing.setAge(employeeUpdate.getAge());
        return employeeRepository.save(existing);
    }
}
