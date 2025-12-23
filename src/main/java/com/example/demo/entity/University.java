package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean active = true;

    // ✅ Default constructor (MANDATORY for tests)
    public University() {
    }

    // ✅ Parameterized constructor
    public University(String name) {
        this.name = name;
        this.active = true;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
