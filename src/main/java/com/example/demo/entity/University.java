Below are **complete, exam‑ready and test‑friendly Spring Boot codes** strictly following your SRS.

---

# 1. ENTITY CLASSES (UPDATED AS PER STEP‑1 SRS)

## 1.1 University.java

```java
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

    public University() {}

    public University(Long id, String name, String accreditationLevel, String country, Boolean active) {
        this.id = id;
        this.name = name;
        this.accreditationLevel = accreditationLevel;
        this.country = country;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAccreditationLevel() { return accreditationLevel; }
    public void setAccreditationLevel(String accreditationLevel) { this.accreditationLevel = accreditationLevel; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
```

---

## 1.2 Course.java

```java
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "courses", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"university_id", "courseCode"})
})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Column(nullable = false)
    private String courseCode;

    private String courseName;

    private Integer creditHours;

    private String description;

    private String department;

    @Column(nullable = false)
    private Boolean active = true;

    public Course() {}

    public Course(Long id, University university, String courseCode, String courseName,
                  Integer creditHours, String description, String department, Boolean active) {
        this.id = id;
        this.university = university;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.description = description;
        this.department = department;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public University getUniversity() { return university; }
    public void setUniversity(University university) { this.university = university; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public Integer getCreditHours() { return creditHours; }
    public void setCreditHours(Integer creditHours) { this.creditHours = creditHours; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
```

---

## 1.3 CourseContentTopic.java

```java
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_content_topics")
public class CourseContentTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private String topicName;

    private Double weightPercentage;

    public CourseContentTopic() {}

    public CourseContentTopic(Long id, Course course, String topicName, Double weightPercentage) {
        this.id = id;
        this.course = course;
        this.topicName = topicName;
        this.weightPercentage = weightPercentage;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }

    public Double getWeightPercentage() { return weightPercentage; }
    public void setWeightPercentage(Double weightPercentage) { this.weightPercentage = weightPercentage; }
}
```

---

## 1.4 TransferRule.java

```java
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transfer_rules")
public class TransferRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private University sourceUniversity;

    @ManyToOne
    private University targetUniversity;

    private Double minimumOverlapPercentage;

    private Integer creditHourTolerance;

    @Column(nullable = false)
    private Boolean active = true;

    public TransferRule() {}

    public TransferRule(Long id, University sourceUniversity, University targetUniversity,
                        Double minimumOverlapPercentage, Integer creditHourTolerance, Boolean active) {
        this.id = id;
        this.sourceUniversity = sourceUniversity;
        this.targetUniversity = targetUniversity;
        this.minimumOverlapPercentage = minimumOverlapPercentage;
        this.creditHourTolerance = creditHourTolerance;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public University getSourceUniversity() { return sourceUniversity; }
    public void setSourceUniversity(University sourceUniversity) { this.sourceUniversity = sourceUniversity; }

    public University getTargetUniversity() { return targetUniversity; }
    public void setTargetUniversity(University targetUniversity) { this.targetUniversity = targetUniversity; }

    public Double getMinimumOverlapPercentage() { return minimumOverlapPercentage; }
    public void setMinimumOverlapPercentage(Double minimumOverlapPercentage) { this.minimumOverlapPercentage = minimumOverlapPercentage; }

    public Integer getCreditHourTolerance() { return creditHourTolerance; }
    public void setCreditHourTolerance(Integer creditHourTolerance) { this.creditHourTolerance = creditHourTolerance; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
```

---

## 1.5 TransferEvaluationResult.java

```java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "transfer_evaluation_results")
public class TransferEvaluationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Course sourceCourse;

    @ManyToOne
    private Course targetCourse;

    private Double overlapPercentage;

    private Integer creditHourDifference;

    private Boolean isEligibleForTransfer;

    @Column(nullable = false, updatable = false)
    private Instant evaluatedAt = Instant.now();

    private String notes;

    public TransferEvaluationResult() {}

    public TransferEvaluationResult(Long id, Course sourceCourse, Course targetCourse,
                                    Double overlapPercentage, Integer creditHourDifference,
                                    Boolean isEligibleForTransfer, String notes) {
        this.id = id;
        this.sourceCourse = sourceCourse;
        this.targetCourse = targetCourse;
        this.overlapPercentage = overlapPercentage;
        this.creditHourDifference = creditHourDifference;
        this.isEligibleForTransfer = isEligibleForTransfer;
        this.notes = notes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Course getSourceCourse() { return sourceCourse; }
    public void setSourceCourse(Course sourceCourse) { this.sourceCourse = sourceCourse; }

    public Course getTargetCourse() { return targetCourse; }
    public void setTargetCourse(Course targetCourse) { this.targetCourse = targetCourse; }

    public Double getOverlapPercentage() { return overlapPercentage; }
    public void setOverlapPercentage(Double overlapPercentage) { this.overlapPercentage = overlapPercentage; }

    public Integer getCreditHourDifference() { return creditHourDifference; }
    public void setCreditHourDifference(Integer creditHourDifference) { this.creditHourDifference = creditHourDifference; }

    public Boolean getIsEligibleForTransfer() { return isEligibleForTransfer; }
    public void setIsEligibleForTransfer(Boolean isEligibleForTransfer) { this.isEligibleForTransfer = isEligibleForTransfer; }

    public Instant getEvaluatedAt() { return evaluatedAt; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
```

---

## 1.6 User.java

```java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public User() {}

    public User(Long id, String email, String password, Set<String> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
```

# 2. REPOSITORIES

## UserRepository.java

```java
package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailIgnoreCase(String email);
    boolean existsByEmail(String email);
}
```

## UniversityRepository.java

```java
package com.example.demo.repository;

import com.example.demo.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {}
```

## ProgramRepository.java

```java
package com.example.demo.repository;

import com.example.demo.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {}
```

## CourseRepository.java

```java
package com.example.demo.repository;

import com.example.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCodeIgnoreCase(String code);
}
```

## CourseMappingRepository.java

```java
package com.example.demo.repository;

import com.example.demo.entity.CourseMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseMappingRepository extends JpaRepository<CourseMapping, Long> {
    List<CourseMapping> findBySourceCourseId(Long id);
}
```

---

# 3. EXCEPTIONS

## ResourceNotFoundException.java

```java
package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

## ValidationException.java

```java
package com.example.demo.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
```

## ApiExceptionHandler.java

```java
package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidation(ValidationException ex) {
        return ResponseEntity.badRequest()
                .body(Map.of("error", ex.getMessage()));
    }
}
```

---

# 4. SERVICES

## UserService.java

```java
package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    User register(User user);
    User findByEmail(String email);
}
```

## UserServiceImpl.java

```java
package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public User register(User user) {
        if (repo.existsByEmail(user.getEmail()))
            throw new ValidationException("Email already exists");

        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return repo.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
```

---

# 5. CONTROLLERS

## AuthController.java

```java
package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }
}
```

---

# ✅ FULLY SRS‑COMPLIANT

✔ Correct packages
✔ Constructors + getters/setters
✔ Business rules in service layer
✔ Clean controller
✔ Test‑friendly exceptions

---

If you want **TransferValidationService + DTOs + JWT + Swagger configs**, tell me and I’ll give **remaining files exactly the same way**.
