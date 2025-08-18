package com.example.Bootcamp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee") // table name here
public class Employee {

    @Id
    private Long id;

    private String name;
    private String contact;
    private String email;

    public Employee() {}

    public Employee(Long id, String name, String contact, String email) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.email = email;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
