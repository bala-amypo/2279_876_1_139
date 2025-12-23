package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "transfer_requests")
public class TransferRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;

    @ManyToOne(optional = false)
    private Program sourceProgram;

    @ManyToOne(optional = false)
    private Program targetProgram;

    @ElementCollection
    @CollectionTable(name = "completed_courses",
            joinColumns = @JoinColumn(name = "transfer_request_id"))
    private List<CompletedCourse> completedCourses;

    private String status;

    public TransferRequest() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public Program getSourceProgram() { return sourceProgram; }
    public void setSourceProgram(Program sourceProgram) { this.sourceProgram = sourceProgram; }

    public Program getTargetProgram() { return targetProgram; }
    public void setTargetProgram(Program targetProgram) { this.targetProgram = targetProgram; }

    public List<CompletedCourse> getCompletedCourses() { return completedCourses; }
    public void setCompletedCourses(List<CompletedCourse> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
