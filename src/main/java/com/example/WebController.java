package com.example;

import com.example.service.DTO.EmployeeRequestDTO;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class WebController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "index"; // The name of the Thymeleaf template
    }

    @GetMapping("/employees/new")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employeeRequestDTO", new EmployeeRequestDTO());
        return "create_employee"; // The name of the Thymeleaf template
    }

//    @PostMapping("/api/employees")
//    public String createEmployee(@ModelAttribute EmployeeRequestDTO employeeRequestDTO) {
//        // Use EmployeeService to create employee
//        employeeService.createEmployee(employeeRequestDTO);
//        // Redirect to the dashboard
//        return "redirect:/";
//    }

}