package com.example;

import com.example.service.DTO.EmployeeRequestDTO;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employees")
    public EmployeeRequestDTO createEmployee(@ModelAttribute EmployeeRequestDTO employeeRequestDTO) {
        return employeeService.createEmployee(employeeRequestDTO);
    }

    @GetMapping("/employees")
    public Iterable<EmployeeRequestDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}