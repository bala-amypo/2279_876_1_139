package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @Autowired
    private UniversityService service;

    @PostMapping
    public University createUniversity(@RequestBody University univ) {
        return service.createUniversity(univ);
    }

    @PutMapping("/{id}")
    public University updateUniversity(@PathVariable Long id,
                                       @RequestBody University univ) {
        return service.updateUniversity(id, univ);
    }

    @GetMapping("/{id}")
    public University getUniversityById(@PathVariable Long id) {
        return service.getUniversityById(id);
    }

    @GetMapping
    public List<University> getAllUniversities() {
        return service.getAllUniversities();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateUniversity(@PathVariable Long id) {
        service.deactivateUniversity(id);
    }
}