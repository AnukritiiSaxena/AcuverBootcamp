package com.example.Bootcamp.controller;

import com.example.Bootcamp.entity.Employee;
import com.example.Bootcamp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees") // 👈 New endpoint
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/name/{name}")
    public Employee getEmployeeByName(@PathVariable String name) {
        return employeeRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Employee not found with name: " + name));
    }

}
