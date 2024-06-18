package com.example.service;

import com.example.Employee;
import com.example.EmployeeRepository;
import com.example.service.DTO.EmployeeRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    private EmployeeRequestDTO convertToDTO(Employee employee) {
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO();
        employeeRequestDTO.setId(employee.getId());
        employeeRequestDTO.setName(employee.getName());
        employeeRequestDTO.setEmail(employee.getEmail());
        return employeeRequestDTO;
    }
}
