package com.example.demo.dto;

import java.util.List;

public class TransferEvaluationRequest {

    private Long sourceProgramId;
    private Long targetProgramId;
    private List<CompletedCourseDTO> completedCourses;

    public TransferEvaluationRequest() {
    }

    public Long getSourceProgramId() {
        return sourceProgramId;
    }

    public void setSourceProgramId(Long sourceProgramId) {
        this.sourceProgramId = sourceProgramId;
    }

    public Long getTargetProgramId() {
        return targetProgramId;
    }

    public void setTargetProgramId(Long targetProgramId) {
        this.targetProgramId = targetProgramId;
    }

    public List<CompletedCourseDTO> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(List<CompletedCourseDTO> completedCourses) {
        this.completedCourses = completedCourses;
    }
}