package com.example.Bootcamp.repository;

import com.example.Bootcamp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByName(String name);  // Custom query method

}

