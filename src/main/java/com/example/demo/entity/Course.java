## STEP 1 – Data Models (Hibernate / JPA)

Below are the **individual entity Java files**, written **separately**, exactly as per the given SRS.

---

### 1️⃣ University.java

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

    private Boolean active = true;

    public University() {
    }

    public University(String name, String accreditationLevel, String country) {
        this.name = name;
        this.accreditationLevel = accreditationLevel;
        this.country = country;
        this.active = true;
    }

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

    public String getAccreditationLevel() {
        return accreditationLevel;
    }

    public void setAccreditationLevel(String accreditationLevel) {
        this.accreditationLevel = accreditationLevel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
```

---

### 2️⃣ Course.java

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

    private Boolean active = true;

    public Course() {
    }

    public Course(University university, String courseCode, String courseName, Integer creditHours) {
        this.university = university;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(Integer creditHours) {
        this.creditHours = creditHours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
