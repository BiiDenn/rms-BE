package com.rms.recruitment.controllers;

import com.rms.recruitment.dto.CreateRecruitmentProcessRequest;
import com.rms.recruitment.dto.RecruitmentProcessResponse;
import com.rms.recruitment.services.RecruitmentProcessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@Tag(name = "Recruitment Process Management", description = "APIs for managing recruitment processes")
public class RecruitmentProcessController {

    private final RecruitmentProcessService recruitmentProcessService;

    public RecruitmentProcessController(RecruitmentProcessService recruitmentProcessService) {
        this.recruitmentProcessService = recruitmentProcessService;
    }

    @GetMapping("/{candidateId}/recruitment-processes")
    @Operation(summary = "Get recruitment processes by candidate ID", 
               description = "Retrieve all recruitment processes for a specific candidate")
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
    @Operation(summary = "Create new recruitment process", 
               description = "Create a new recruitment process for a specific candidate")
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
}
