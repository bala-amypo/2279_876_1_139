package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "universities", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String country;

    public University() {}

    public University(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
