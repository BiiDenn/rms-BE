package com.rms.recruitment.controllers;

import com.rms.recruitment.dto.CandidateProcessTimelineItemResponse;
import com.rms.recruitment.dto.CreateRecruitmentProcessRequest;
import com.rms.recruitment.dto.RecruitmentProcessResponse;
import com.rms.recruitment.dto.InterviewOverviewResponse;
import com.rms.recruitment.services.RecruitmentProcessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@Tag(name = "Candidate Process Management", description = "APIs for managing candidate processes")
public class CandidateProcessController {

    private final RecruitmentProcessService recruitmentProcessService;

    public CandidateProcessController(RecruitmentProcessService recruitmentProcessService) {
        this.recruitmentProcessService = recruitmentProcessService;
    }

    @GetMapping("/{candidateId}/recruitment-processes")
    @Operation(summary = "Get candidate's recruitment processes", 
               description = "Retrieve all recruitment processes linked to a candidate")
    public ResponseEntity<List<RecruitmentProcessResponse>> getRecruitmentProcessesByCandidateId(
            @PathVariable Integer candidateId) {
        try {
            List<RecruitmentProcessResponse> processes = 
                recruitmentProcessService.getRecruitmentProcessesByCandidateId(candidateId);
            return ResponseEntity.ok(processes);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{candidateId}/recruitment-processes")
    @Operation(summary = "Create candidate recruitment process", 
               description = "Create and link a new recruitment process for the candidate")
    public ResponseEntity<RecruitmentProcessResponse> createRecruitmentProcess(
            @PathVariable Integer candidateId,
            @Valid @RequestBody CreateRecruitmentProcessRequest request) {
        try {
            RecruitmentProcessResponse response = 
                recruitmentProcessService.createRecruitmentProcess(candidateId, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{candidateId}/recruitment-processes/{recruitProcessId}/processes")
    @Operation(summary = "Get candidate timeline for a recruitment process",
               description = "Return all candidate processes for the given candidate and recruitment process")
    public ResponseEntity<List<CandidateProcessTimelineItemResponse>> getCandidateProcessTimeline(
            @PathVariable Integer candidateId,
            @PathVariable Integer recruitProcessId) {
        List<CandidateProcessTimelineItemResponse> timeline =
                recruitmentProcessService.getCandidateProcessTimeline(candidateId, recruitProcessId);
        return ResponseEntity.ok(timeline);
    }

    @GetMapping("/{candidateId}/recruitment-processes/{recruitProcessId}/interview-evaluation/overview")
    @Operation(summary = "Get interview overview",
            description = "Return candidate info, interview session info, and interviewer info")
    public ResponseEntity<InterviewOverviewResponse> getInterviewOverview(
            @PathVariable Integer candidateId,
            @PathVariable Integer recruitProcessId) {
        InterviewOverviewResponse overview = recruitmentProcessService.getInterviewOverview(candidateId, recruitProcessId);
        return ResponseEntity.ok(overview);
    }
}
