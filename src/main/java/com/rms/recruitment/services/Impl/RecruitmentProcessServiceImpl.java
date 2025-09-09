package com.rms.recruitment.services.Impl;

import com.rms.recruitment.dto.CandidateProcessTimelineItemResponse;
import com.rms.recruitment.dto.CreateRecruitmentProcessRequest;
import com.rms.recruitment.dto.RecruitmentProcessResponse;
import com.rms.recruitment.dto.InterviewOverviewResponse;
import com.rms.recruitment.dto.InterviewOverviewResponseCandidate;
import com.rms.recruitment.dto.InterviewOverviewResponseSession;
import com.rms.recruitment.dto.InterviewOverviewResponseInterviewer;
import com.rms.recruitment.models.CandidateProcess;
import com.rms.recruitment.models.RecruitmentProcess;
import com.rms.recruitment.repositories.CandidateProcessRepository;
import com.rms.recruitment.repositories.CandidatesRepository;
import com.rms.recruitment.repositories.RecruitmentProcessRepository;
import com.rms.recruitment.services.RecruitmentProcessService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruitmentProcessServiceImpl implements RecruitmentProcessService {

    private final RecruitmentProcessRepository recruitmentProcessRepository;
    private final CandidatesRepository candidatesRepository;
    private final CandidateProcessRepository candidateProcessRepository;

    public RecruitmentProcessServiceImpl(RecruitmentProcessRepository recruitmentProcessRepository,
                                       CandidatesRepository candidatesRepository,
                                       CandidateProcessRepository candidateProcessRepository) {
        this.recruitmentProcessRepository = recruitmentProcessRepository;
        this.candidatesRepository = candidatesRepository;
        this.candidateProcessRepository = candidateProcessRepository;
    }

    @Override
    public List<RecruitmentProcessResponse> getRecruitmentProcessesByCandidateId(Integer candidateId) {
        List<RecruitmentProcess> processes = recruitmentProcessRepository.findByCandidateId(candidateId);
        // Debug log
        System.out.println("Found " + processes.size() + " processes for candidate " + candidateId);
        for (RecruitmentProcess process : processes) {
            System.out.println("Process ID: " + process.getRecruitProcessId());
            System.out.println("Status: " + process.getRecruitProcessStatus());
            if (process.getRecruitment() != null) {
                System.out.println("Recruitment Title: " + process.getRecruitment().getTitle());
                System.out.println("Recruitment Code: " + process.getRecruitment().getRecruitmentCode());
                if (process.getRecruitment().getDivision() != null) {
                    System.out.println("Division: " + process.getRecruitment().getDivision().getDivisionName());
                }
            }
        }
        return processes.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RecruitmentProcessResponse createRecruitmentProcess(Integer candidateId, CreateRecruitmentProcessRequest request) {
        System.out.println("=== CREATE RECRUITMENT PROCESS DEBUG ===");
        System.out.println("Candidate ID: " + candidateId);
        System.out.println("RecruitProcess ID: " + request.getRecruitProcessId());
        System.out.println("Status: " + request.getRecruitProcessStatus());

        // Validate candidate exists
        candidatesRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + candidateId));

        // Require existing recruitment_process; do not create a new one
        RecruitmentProcess savedProcess = recruitmentProcessRepository.findByIdWithRelations(request.getRecruitProcessId())
                .orElseThrow(() -> new RuntimeException("Recruitment process not found: " + request.getRecruitProcessId()));
        System.out.println("Using existing Process ID: " + savedProcess.getRecruitProcessId());

        // Fetch the saved process with relationships
        RecruitmentProcess processWithRelations = recruitmentProcessRepository.findByIdWithRelations(savedProcess.getRecruitProcessId())
                .orElseThrow(() -> new RuntimeException("Failed to fetch created process"));

        // For linking-only flow, we simply use the fetched process with relations
        RecruitmentProcess processToReturn = processWithRelations;

        System.out.println("Final Process - ID: " + processToReturn.getRecruitProcessId());
        System.out.println("Final Process - Status: " + processToReturn.getRecruitProcessStatus());
        System.out.println("Final Process - Recruit ID: " + processToReturn.getRecruitId());
        if (processToReturn.getRecruitment() != null) {
            System.out.println("Recruitment Title: " + processToReturn.getRecruitment().getTitle());
            System.out.println("Recruitment Code: " + processToReturn.getRecruitment().getRecruitmentCode());
            if (processToReturn.getRecruitment().getDivision() != null) {
                System.out.println("Division Name: " + processToReturn.getRecruitment().getDivision().getDivisionName());
            } else {
                System.out.println("Division is NULL");
            }
        } else {
            System.out.println("Recruitment is NULL");
        }

        // Auto-create 7 default candidate process steps for this candidate and recruitment process
        System.out.println("=== CREATING DEFAULT CANDIDATE PROCESS STEPS ===");
        List<String> defaultSteps = Arrays.asList(
                "addNew",
                "Screening",
                "Call Interview",
                "Interview1",
                "Interview Evaluation",
                "Job Offer",
                "Onboarding"
        );
        LocalDate today = LocalDate.now();
        List<CandidateProcess> toSave = new ArrayList<>();
        for (String step : defaultSteps) {
            CandidateProcess cp = CandidateProcess.builder()
                    .candId(candidateId)
                    .recruitProcessId(savedProcess.getRecruitProcessId())
                    .processDate(today)
                    .status("Pending")
                    .candidateProcessType(step)
                    .build();
            toSave.add(cp);
        }
        List<CandidateProcess> savedSteps = candidateProcessRepository.saveAll(toSave);
        System.out.println("Created " + savedSteps.size() + " default candidate process steps.");

        return mapToResponse(processToReturn);
    }

    @Override
    public List<CandidateProcessTimelineItemResponse> getCandidateProcessTimeline(Integer candidateId, Integer recruitProcessId) {
        List<CandidateProcess> items = candidateProcessRepository
                .findTimelineByCandidateAndRecruitProcess(candidateId, recruitProcessId);

        return items.stream().map(cp -> {
            String typeName = cp.getCandProcessType() != null ? cp.getCandProcessType().getName() : null;
            String interviewerName = cp.getInterviewer() != null ?
                    String.format("%s %s",
                            cp.getInterviewer().getFirstName() != null ? cp.getInterviewer().getFirstName() : "",
                            cp.getInterviewer().getLastName() != null ? cp.getInterviewer().getLastName() : ""
                    ).trim() : null;
            String interviewerEmail = cp.getInterviewerEmail();
            String locationName = cp.getLocation() != null ? cp.getLocation().getLocationName() : null;

            return CandidateProcessTimelineItemResponse.builder()
                    .id(cp.getCandProcessId())
                    .processDate(cp.getProcessDate())
                    .typeId(cp.getCandProcessTypeId())
                    .typeName(typeName != null ? typeName : cp.getCandidateProcessType())
                    .status(cp.getStatus())
                    .description(cp.getDescription())
                    .interviewerId(cp.getInterviewerId())
                    .interviewerName(interviewerName)
                    .interviewerEmail(interviewerEmail)
                    .locationId(cp.getLocationId())
                    .locationName(locationName)
                    .url(cp.getUrl())
                    .note(cp.getNote())
                    .build();
        }).collect(Collectors.toList());
    }

    public InterviewOverviewResponse getInterviewOverview(Integer candidateId, Integer recruitProcessId) {
        // Candidate info
        var candidate = candidatesRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found: " + candidateId));

        // Recruitment info via recruitProcessId
        var rpOpt = recruitmentProcessRepository.fetchRecruitmentInfoByProcessId(recruitProcessId);
        var rp = rpOpt.orElseThrow(() -> new RuntimeException("Recruitment process not found: " + recruitProcessId));

        String fullName = String.format("%s %s",
                candidate.getFirstName() != null ? candidate.getFirstName() : "",
                candidate.getLastName() != null ? candidate.getLastName() : "").trim();

        String department = rp.getRecruitment() != null && rp.getRecruitment().getDivision() != null
                ? rp.getRecruitment().getDivision().getDivisionName() : null;

        // Job title: from JobTitles by recruitId
        String jobTitleName = null;
        if (rp.getRecruitment() != null && rp.getRecruitment().getRecruitId() != null) {
            // We can traverse JobTitles by recruitment relation on JobTitles
            // For simplicity, pick the first JobTitle linked to this recruitment if available via candidate
            if (candidate.getJobTitle() != null && candidate.getJobTitle().getRecruitId() != null
                    && candidate.getJobTitle().getRecruitId().equals(rp.getRecruitment().getRecruitId())) {
                jobTitleName = candidate.getJobTitle().getName();
            }
        }

        InterviewOverviewResponseCandidate candidateInfo = InterviewOverviewResponseCandidate.builder()
                .fullName(fullName)
                .dateOfBirth(candidate.getDateOfBirth())
                .maritalStatus(candidate.getMaritalStatus())
                .expectedOnboardDate(candidate.getExpectedOnboardDate())
                .jobTitle(jobTitleName)
                .department(department)
                .build();

        InterviewOverviewResponseSession sessionInfo = InterviewOverviewResponseSession.builder()
                .mode(null)
                .branch(null)
                .time(null)
                .interviewRounds(null)
                .build();

        InterviewOverviewResponseInterviewer interviewerInfo = InterviewOverviewResponseInterviewer.builder()
                .interviewerName(null)
                .interviewerTitle(null)
                .interviewerEmail(null)
                .build();

        return InterviewOverviewResponse.builder()
                .candidateInfo(candidateInfo)
                .interviewSessionInfo(sessionInfo)
                .interviewerInfo(interviewerInfo)
                .build();
    }

    private RecruitmentProcessResponse mapToResponse(RecruitmentProcess process) {
        String recruitmentTitle = "N/A";
        String code = "N/A";
        String position = "N/A";
        String department = "N/A";
        if (process.getRecruitment() != null) {
            recruitmentTitle = process.getRecruitment().getTitle() != null ?
                    process.getRecruitment().getTitle() : "N/A";
            code = process.getRecruitment().getRecruitmentCode() != null ?
                    process.getRecruitment().getRecruitmentCode() : "N/A";
            if (process.getRecruitment().getDivision() != null) {
                position = process.getRecruitment().getDivision().getDivisionName() != null ?
                        process.getRecruitment().getDivision().getDivisionName() : "N/A";
                department = process.getRecruitment().getDivision().getDivisionName() != null ?
                        process.getRecruitment().getDivision().getDivisionName() : "N/A";
            }
        }
        String status = process.getRecruitProcessStatus() != null ?
                process.getRecruitProcessStatus() : "N/A";
        System.out.println("Mapping - Title: " + recruitmentTitle + ", Status: " + status + ", Code: " + code);
        return RecruitmentProcessResponse.builder()
                .recruitmentTitle(recruitmentTitle)
                .status(status)
                .code(code)
                .position(position)
                .department(department)
                .build();
    }
}
