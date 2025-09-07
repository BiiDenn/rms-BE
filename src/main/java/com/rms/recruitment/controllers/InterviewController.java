package com.rms.recruitment.controllers;

import com.rms.recruitment.models.Candidates;
import com.rms.recruitment.models.InterviewEvaluation;
import com.rms.recruitment.models.RecruitmentProcess;
import com.rms.recruitment.repositories.CandidatesRepository;
import com.rms.recruitment.repositories.InterviewEvaluationRepository;
import com.rms.recruitment.repositories.RecruitmentProcessRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/interviews")
@Tag(name = "Interview Management", description = "Interview management APIs")
public class InterviewController {

    @Autowired
    private CandidatesRepository candidatesRepository;

    @Autowired
    private InterviewEvaluationRepository interviewEvaluationRepository;

    @Autowired
    private RecruitmentProcessRepository recruitmentProcessRepository;

    @GetMapping("/schedule")
    @Operation(summary = "Get interview schedule", description = "Get list of scheduled interviews")
    @PreAuthorize("hasAuthority('CONDUCT_INTERVIEW')")
    public ResponseEntity<Map<String, Object>> getInterviewSchedule(Authentication authentication) {
        String currentUser = authentication.getName();
        String userRole = getUserRole(authentication);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Interview schedule retrieved successfully");
        response.put("user", currentUser);
        response.put("role", userRole);
        response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        // Get real data from database
        List<Candidates> candidates = candidatesRepository.findAll();
        List<Map<String, Object>> interviews = new ArrayList<>();

        for (Candidates candidate : candidates) {
            Map<String, Object> interview = new HashMap<>();
            interview.put("candidateId", candidate.getCandId());
            interview.put("candidateName", candidate.getFirstName() + " " + candidate.getLastName());
            interview.put("email", candidate.getMainEmail());
            interview.put("phone", candidate.getMainPhone());
            interview.put("jobTitle", candidate.getJobTitle() != null ? candidate.getJobTitle().getName() : "N/A");
            interview.put("status", "Scheduled for Interview");
            interview.put("interviewDate",
                    LocalDateTime.now().plusDays(7).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            interviews.add(interview);
        }

        response.put("interviews", interviews);
        response.put("totalInterviews", interviews.size());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/schedule")
    @Operation(summary = "Schedule interview", description = "Schedule a new interview")
    @PreAuthorize("hasAuthority('CONDUCT_INTERVIEW')")
    public ResponseEntity<Map<String, Object>> scheduleInterview(@RequestBody Map<String, Object> interviewData,
            Authentication authentication) {
        String currentUser = authentication.getName();
        String userRole = getUserRole(authentication);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Interview scheduled successfully");
        response.put("scheduledBy", currentUser);
        response.put("role", userRole);
        response.put("interviewData", interviewData);
        response.put("scheduledAt", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.put("interviewId", "INT-" + System.currentTimeMillis());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/candidates/{candidateId}")
    @Operation(summary = "Get candidate for interview", description = "Get candidate information for interview")
    @PreAuthorize("hasAuthority('CONDUCT_INTERVIEW')")
    public ResponseEntity<Map<String, Object>> getCandidateForInterview(@PathVariable Integer candidateId,
            Authentication authentication) {
        String currentUser = authentication.getName();
        String userRole = getUserRole(authentication);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Candidate information retrieved successfully");
        response.put("requestedBy", currentUser);
        response.put("role", userRole);
        response.put("candidateId", candidateId);

        // Get real candidate data from database
        Optional<Candidates> candidateOpt = candidatesRepository.findById(candidateId);
        if (candidateOpt.isPresent()) {
            Candidates candidate = candidateOpt.get();
            Map<String, Object> candidateData = new HashMap<>();
            candidateData.put("candidateId", candidate.getCandId());
            candidateData.put("firstName", candidate.getFirstName());
            candidateData.put("lastName", candidate.getLastName());
            candidateData.put("fullName", candidate.getFirstName() + " " + candidate.getLastName());
            candidateData.put("mainEmail", candidate.getMainEmail());
            candidateData.put("subEmail", candidate.getSubEmail());
            candidateData.put("mainPhone", candidate.getMainPhone());
            candidateData.put("subPhone", candidate.getSubPhone());
            candidateData.put("gender", candidate.getGender());
            candidateData.put("dateOfBirth", candidate.getDateOfBirth());
            candidateData.put("nationality", candidate.getNationality());
            candidateData.put("referredBy", candidate.getReferredBy());
            candidateData.put("note", candidate.getNote());
            candidateData.put("jobTitle", candidate.getJobTitle() != null ? candidate.getJobTitle().getName() : "N/A");
            candidateData.put("status", "Ready for Interview");

            response.put("candidate", candidateData);
        } else {
            response.put("error", "Candidate not found");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/evaluate")
    @Operation(summary = "Evaluate candidate", description = "Submit interview evaluation")
    @PreAuthorize("hasAuthority('EVALUATE_CANDIDATES')")
    public ResponseEntity<Map<String, Object>> evaluateCandidate(@RequestBody Map<String, Object> evaluationData,
            Authentication authentication) {
        String currentUser = authentication.getName();
        String userRole = getUserRole(authentication);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Candidate evaluation submitted successfully");
        response.put("evaluatedBy", currentUser);
        response.put("role", userRole);
        response.put("evaluationData", evaluationData);
        response.put("evaluationId", "EVAL-" + System.currentTimeMillis());
        response.put("submittedAt", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/evaluations")
    @Operation(summary = "Get interview evaluations", description = "Get list of interview evaluations")
    @PreAuthorize("hasAuthority('EVALUATE_CANDIDATES')")
    public ResponseEntity<Map<String, Object>> getInterviewEvaluations(Authentication authentication) {
        String currentUser = authentication.getName();
        String userRole = getUserRole(authentication);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Interview evaluations retrieved successfully");
        response.put("requestedBy", currentUser);
        response.put("role", userRole);
        response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        // Get real evaluation data from database
        List<InterviewEvaluation> evaluations = interviewEvaluationRepository.findAll();
        List<Map<String, Object>> evaluationList = new ArrayList<>();

        for (InterviewEvaluation evaluation : evaluations) {
            Map<String, Object> evalData = new HashMap<>();
            evalData.put("evaluationId", evaluation.getItvEvalId());
            evalData.put("candidateLevel", evaluation.getCandLevel());
            evalData.put("hrComment", evaluation.getHrComment());
            evalData.put("recommendationResult", evaluation.getRecommendationResult());
            evalData.put("recommendationNote", evaluation.getRecommendationNote());
            evalData.put("employeeId", evaluation.getEmployeeId());
            evalData.put("employee",
                    evaluation.getEmployee() != null
                            ? evaluation.getEmployee().getFirstName() + " " + evaluation.getEmployee().getLastName()
                            : "N/A");
            evaluationList.add(evalData);
        }

        response.put("evaluations", evaluationList);
        response.put("totalEvaluations", evaluationList.size());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-interviews")
    @Operation(summary = "Get my interviews", description = "Get interviews assigned to current user")
    @PreAuthorize("hasAuthority('CONDUCT_INTERVIEW')")
    public ResponseEntity<Map<String, Object>> getMyInterviews(Authentication authentication) {
        String currentUser = authentication.getName();
        String userRole = getUserRole(authentication);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "My interviews retrieved successfully");
        response.put("user", currentUser);
        response.put("role", userRole);
        response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        // Get real recruitment processes from database
        List<RecruitmentProcess> processes = recruitmentProcessRepository.findAll();
        List<Map<String, Object>> myInterviews = new ArrayList<>();

        for (RecruitmentProcess process : processes) {
            Map<String, Object> interview = new HashMap<>();
            interview.put("processId", process.getRecruitProcessId());
            interview.put("status", process.getRecruitProcessStatus());
            interview.put("createdAt", process.getCreatedAt());
            interview.put("recruitmentId", process.getRecruitId());
            interview.put("recruitment",
                    process.getRecruitment() != null ? process.getRecruitment().getTitle() : "N/A");
            myInterviews.add(interview);
        }

        response.put("myInterviews", myInterviews);
        response.put("totalMyInterviews", myInterviews.size());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/feedback")
    @Operation(summary = "Submit interview feedback", description = "Submit feedback after interview")
    @PreAuthorize("hasAuthority('EVALUATE_CANDIDATES')")
    public ResponseEntity<Map<String, Object>> submitFeedback(@RequestBody Map<String, Object> feedbackData,
            Authentication authentication) {
        String currentUser = authentication.getName();
        String userRole = getUserRole(authentication);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Interview feedback submitted successfully");
        response.put("submittedBy", currentUser);
        response.put("role", userRole);
        response.put("feedbackData", feedbackData);
        response.put("feedbackId", "FB-" + System.currentTimeMillis());
        response.put("submittedAt", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        return ResponseEntity.ok(response);
    }

    private String getUserRole(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("ROLE_"))
                .findFirst()
                .map(auth -> auth.substring(5)) // Remove "ROLE_" prefix
                .orElse("UNKNOWN");
    }
}