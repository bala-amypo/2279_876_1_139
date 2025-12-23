package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "programs", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"university_id", "name"})
})
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private University university;

    @Column(nullable = false)
    private String name;

    private String level;

    public Program() {}

    public Program(University university, String name, String level) {
        this.university = university;
        this.name = name;
        this.level = level;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public University getUniversity() { return university; }
    public void setUniversity(University university) { this.university = university; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
}
