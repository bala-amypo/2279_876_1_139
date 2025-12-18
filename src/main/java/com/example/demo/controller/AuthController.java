// ===============================
// Package for all controllers
// ===============================
// package com.example.transferportal.controller;

// ==================================================
// UniversityController.java
// ==================================================
package com.example.transferportal.controller;

import com.example.transferportal.entity.University;
import com.example.transferportal.service.UniversityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/universities")
@Tag(name = "University")
public class UniversityController {

    private final UniversityService service;

    public UniversityController(UniversityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<University> create(@RequestBody University university) {
        return ResponseEntity.ok(service.create(university));
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> update(@PathVariable Long id, @RequestBody University university) {
        return ResponseEntity.ok(service.update(id, university));
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivate(id);
        return ResponseEntity.ok().build();
    }
}

// ==================================================
// CourseController.java
// ==================================================
package com.example.transferportal.controller;

import com.example.transferportal.entity.Course;
import com.example.transferportal.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Course")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return ResponseEntity.ok(service.create(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
        return ResponseEntity.ok(service.update(id, course));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/university/{universityId}")
    public ResponseEntity<?> getByUniversity(@PathVariable Long universityId) {
        return ResponseEntity.ok(service.getByUniversity(universityId));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivate(id);
        return ResponseEntity.ok().build();
    }
}

// ==================================================
// CourseContentTopicController.java
// ==================================================
package com.example.transferportal.controller;

import com.example.transferportal.entity.CourseContentTopic;
import com.example.transferportal.service.CourseContentTopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics")
@Tag(name = "Course Content Topic")
public class CourseContentTopicController {

    private final CourseContentTopicService service;

    public CourseContentTopicController(CourseContentTopicService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CourseContentTopic> create(@RequestBody CourseContentTopic topic) {
        return ResponseEntity.ok(service.create(topic));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseContentTopic> update(@PathVariable Long id, @RequestBody CourseContentTopic topic) {
        return ResponseEntity.ok(service.update(id, topic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseContentTopic> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(service.getByCourse(courseId));
    }
}

// ==================================================
// TransferRuleController.java
// ==================================================
package com.example.transferportal.controller;

import com.example.transferportal.entity.TransferRule;
import com.example.transferportal.service.TransferRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer-rules")
@Tag(name = "Transfer Rule")
public class TransferRuleController {

    private final TransferRuleService service;

    public TransferRuleController(TransferRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransferRule> create(@RequestBody TransferRule rule) {
        return ResponseEntity.ok(service.create(rule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferRule> update(@PathVariable Long id, @RequestBody TransferRule rule) {
        return ResponseEntity.ok(service.update(id, rule));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferRule> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/pair/{sourceId}/{targetId}")
    public ResponseEntity<?> getByPair(@PathVariable Long sourceId, @PathVariable Long targetId) {
        return ResponseEntity.ok(service.getByPair(sourceId, targetId));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivate(id);
        return ResponseEntity.ok().build();
    }
}

// ==================================================
// TransferEvaluationController.java
// ==================================================
package com.example.transferportal.controller;

import com.example.transferportal.entity.TransferEvaluationResult;
import com.example.transferportal.service.TransferEvaluationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer-evaluations")
@Tag(name = "Transfer Evaluation")
public class TransferEvaluationController {

    private final TransferEvaluationService service;

    public TransferEvaluationController(TransferEvaluationService service) {
        this.service = service;
    }

    @PostMapping("/evaluate/{sourceCourseId}/{targetCourseId}")
    public ResponseEntity<TransferEvaluationResult> evaluate(
            @PathVariable Long sourceCourseId,
            @PathVariable Long targetCourseId) {
        return ResponseEntity.ok(service.evaluate(sourceCourseId, targetCourseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferEvaluationResult> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(service.getByCourse(courseId));
    }
}

// ==================================================
// AuthController.java
// ==================================================
package com.example.transferportal.controller;

import com.example.transferportal.dto.AuthResponse;
import com.example.transferportal.dto.LoginRequest;
import com.example.transferportal.dto.RegisterRequest;
import com.example.transferportal.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}
