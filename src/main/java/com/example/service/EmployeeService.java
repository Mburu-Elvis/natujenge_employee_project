package com.example.service;

import com.example.Employee;
import com.example.EmployeeRepository;
import com.example.service.DTO.EmployeeRequestDTO;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    // Logic to handle Employees
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeRequestDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();
        employee.setName(employeeRequestDTO.getName());
        employee.setEmail(employeeRequestDTO.getEmail());
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    public List<EmployeeRequestDTO> getAllEmployees() {
        Iterable<Employee> employees = employeeRepository.findAll();

        List<EmployeeRequestDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee: employees) {
            employeeDTOs.add(convertToDTO(employee));
        }
        return  employeeDTOs;
    }

    public EmployeeRequestDTO updateEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Integer id = employeeRequestDTO.getId();

        // Find the existing employee by ID
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        // Check if the employee exists
        if (!optionalEmployee.isPresent()) {
            return employeeRequestDTO; // Or handle it with a custom exception
        }

        // Get the existing employee entity
        Employee existingEmployee = optionalEmployee.get();

        // Update the existing employee with new values
        existingEmployee.setName(employeeRequestDTO.getName());
        existingEmployee.setEmail(employeeRequestDTO.getEmail());

        // Save the updated employee entity
        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        // Convert to DTO and return
        return convertToDTO(updatedEmployee);
    }

    private EmployeeRequestDTO convertToDTO(Employee employee) {
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO();
        employeeRequestDTO.setId(employee.getId());
        employeeRequestDTO.setName(employee.getName());
        employeeRequestDTO.setEmail(employee.getEmail());
        return employeeRequestDTO;
    }
}
