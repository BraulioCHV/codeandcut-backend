package org.code_cut.code_cutSpring.service;

import org.code_cut.code_cutSpring.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee addEmployee(Employee employee);
    void deleteEmployeeById(Long id);
    Employee updateEmployeeById(Long id, Employee employeeUpdate);
}
