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

    @Column(nullable = false, unique = true)
    private String name;

    private String accreditationLevel;
    private String country;

    @Column(nullable = false)
    private Boolean active = true;

    
    public University() {
        
    }

    
    public University(String name, String accreditationLevel, String country) {
        this.name = name;
        this.accreditationLevel = accreditationLevel;
        this.country = country;
        this.active = true;
    }

    
}
