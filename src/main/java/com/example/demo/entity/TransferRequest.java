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

    @ManyToOne
    @JoinColumn(name = "source_program_id")
    private Program sourceProgram;

    @ManyToOne
    @JoinColumn(name = "target_program_id")
    private Program targetProgram;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CompletedCourse> completedCourses;

    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    public TransferRequest() {
    }

    public TransferRequest(
            String studentId,
            Program sourceProgram,
            Program targetProgram,
            List<CompletedCourse> completedCourses,
            TransferStatus status) {

        this.studentId = studentId;
        this.sourceProgram = sourceProgram;
        this.targetProgram = targetProgram;
        this.completedCourses = completedCourses;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Program getSourceProgram() {
        return sourceProgram;
    }

    public void setSourceProgram(Program sourceProgram) {
        this.sourceProgram = sourceProgram;
    }

    public Program getTargetProgram() {
        return targetProgram;
    }

    public void setTargetProgram(Program targetProgram) {
        this.targetProgram = targetProgram;
    }

    public List<CompletedCourse> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(List<CompletedCourse> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }
}