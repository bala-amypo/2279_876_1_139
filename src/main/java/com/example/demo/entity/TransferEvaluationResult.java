
```

---

## 1.3 Program.java

```java
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    private String name;
    private String level;

    public Program() {}

    public Program(Long id, University university, String name, String level) {
        this.id = id;
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
```

---

## 1.4 Course.java

```java

```

---

## 1.5 CourseMapping.java

```java
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_mappings")
public class CourseMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Course sourceCourse;

    @ManyToOne
    private Course targetCourse;

    private String equivalencyType;
    private String minGradeRequired;

    public CourseMapping() {}

    public CourseMapping(Long id, Course sourceCourse, Course targetCourse,
                         String equivalencyType, String minGradeRequired) {
        this.id = id;
        this.sourceCourse = sourceCourse;
        this.targetCourse = targetCourse;
        this.equivalencyType = equivalencyType;
        this.minGradeRequired = minGradeRequired;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Course getSourceCourse() { return sourceCourse; }
    public void setSourceCourse(Course sourceCourse) { this.sourceCourse = sourceCourse; }

    public Course getTargetCourse() { return targetCourse; }
    public void setTargetCourse(Course targetCourse) { this.targetCourse = targetCourse; }

    public String getEquivalencyType() { return equivalencyType; }
    public void setEquivalencyType(String equivalencyType) { this.equivalencyType = equivalencyType; }

    public String getMinGradeRequired() { return minGradeRequired; }
    public void setMinGradeRequired(String minGradeRequired) { this.minGradeRequired = minGradeRequired; }
}
```

---

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
