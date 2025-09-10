package com.rms.recruitment.services.Impl;

import com.rms.recruitment.dto.CandidateProcessTimelineItemResponse;
import com.rms.recruitment.dto.CreateRecruitmentProcessRequest;
import com.rms.recruitment.dto.RecruitmentProcessResponse;
import com.rms.recruitment.dto.InterviewOverviewResponse;
import com.rms.recruitment.dto.InterviewOverviewResponseCandidate;
import com.rms.recruitment.dto.InterviewOverviewResponseSession;
import com.rms.recruitment.dto.InterviewOverviewResponseInterviewer;
import com.rms.recruitment.dto.JobOfferResponse;
import com.rms.recruitment.dto.CreateOfferRequest;
import com.rms.recruitment.dto.OfferResponse;
import com.rms.recruitment.dto.OnboardingInfoResponse;
import com.rms.recruitment.models.CandidateProcess;
import com.rms.recruitment.models.RecruitmentProcess;
import com.rms.recruitment.models.Offers;
import com.rms.recruitment.repositories.CandidateProcessRepository;
import com.rms.recruitment.repositories.CandidatesRepository;
import com.rms.recruitment.repositories.RecruitmentProcessRepository;
import com.rms.recruitment.repositories.OffersRepository;
import com.rms.recruitment.services.RecruitmentProcessService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecruitmentProcessServiceImpl implements RecruitmentProcessService {

    private final RecruitmentProcessRepository recruitmentProcessRepository;
    private final CandidatesRepository candidatesRepository;
    private final CandidateProcessRepository candidateProcessRepository;
    private final OffersRepository offersRepository;

    public RecruitmentProcessServiceImpl(RecruitmentProcessRepository recruitmentProcessRepository,
                                       CandidatesRepository candidatesRepository,
                                       CandidateProcessRepository candidateProcessRepository,
                                       OffersRepository offersRepository) {
        this.recruitmentProcessRepository = recruitmentProcessRepository;
        this.candidatesRepository = candidatesRepository;
        this.candidateProcessRepository = candidateProcessRepository;
        this.offersRepository = offersRepository;
    }

    @Override
    public List<RecruitmentProcessResponse> getRecruitmentProcessesByCandidateId(Integer candidateId) {
        // Get from candidate_process table, group by recruitProcessId
        List<CandidateProcess> candidateProcesses = candidateProcessRepository.findByCandId(candidateId);
        
        // Group by recruitProcessId and get unique recruitment processes
        Map<Integer, List<CandidateProcess>> groupedByRecruitProcess = candidateProcesses.stream()
                .collect(Collectors.groupingBy(CandidateProcess::getRecruitProcessId));
        
        List<RecruitmentProcessResponse> responses = new ArrayList<>();
        for (Map.Entry<Integer, List<CandidateProcess>> entry : groupedByRecruitProcess.entrySet()) {
            Integer recruitProcessId = entry.getKey();
            List<CandidateProcess> processes = entry.getValue();
            
            if (!processes.isEmpty()) {
                // Get recruitment process details
                RecruitmentProcess recruitmentProcess = recruitmentProcessRepository
                        .findByIdWithRelations(recruitProcessId)
                        .orElse(null);
                
                if (recruitmentProcess != null) {
                    responses.add(mapToResponse(recruitmentProcess));
                }
            }
        }
        
        return responses;
    }

    @Override
    public RecruitmentProcessResponse createRecruitmentProcess(Integer candidateId, CreateRecruitmentProcessRequest request) {
        System.out.println("=== CREATE RECRUITMENT PROCESS DEBUG ===");
        System.out.println("Candidate ID: " + candidateId);
        System.out.println("Recruit ID: " + request.getRecruitId());
        System.out.println("Status: " + request.getRecruitProcessStatus());

        // Validate candidate exists
        candidatesRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + candidateId));

        // Create new recruitment_process
        RecruitmentProcess newProcess = RecruitmentProcess.builder()
                .recruitId(request.getRecruitId())
                .recruitProcessStatus(request.getRecruitProcessStatus() != null ? 
                        request.getRecruitProcessStatus() : "In Progress")
                .createdAt(LocalDateTime.now())
                .build();
        
        RecruitmentProcess savedProcess = recruitmentProcessRepository.save(newProcess);
        System.out.println("Created new Process ID: " + savedProcess.getRecruitProcessId());

        // Fetch the saved process with relationships
        RecruitmentProcess processWithRelations = recruitmentProcessRepository.findByIdWithRelations(savedProcess.getRecruitProcessId())
                .orElseThrow(() -> new RuntimeException("Failed to fetch created process"));

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
                "AddNew",
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
                    .candProcessName(step)
                    .build();
            toSave.add(cp);
        }
        List<CandidateProcess> savedSteps = candidateProcessRepository.saveAll(toSave);
        System.out.println("Created " + savedSteps.size() + " default candidate process steps.");

        return mapToResponse(processToReturn);
    }

    public RecruitmentProcessRepository getRecruitmentProcessRepository() {
        return recruitmentProcessRepository;
    }

    public CandidatesRepository getCandidatesRepository() {
        return candidatesRepository;
    }

    public CandidateProcessRepository getCandidateProcessRepository() {
        return candidateProcessRepository;
    }

    @Override
    public List<CandidateProcessTimelineItemResponse> getCandidateProcessTimeline(Integer candidateId, Integer recruitProcessId) {
        List<CandidateProcess> items = candidateProcessRepository
                .findTimelineByCandidateAndRecruitProcess(candidateId, recruitProcessId);

        return items.stream().<CandidateProcessTimelineItemResponse>map(cp -> {
            String typeName = cp.getCandProcessName();
            String interviewerName = cp.getInterviewer() != null ?
                    String.format("%s %s",
                            cp.getInterviewer().getFirstName() != null ? cp.getInterviewer().getFirstName() : "",
                            cp.getInterviewer().getLastName() != null ? cp.getInterviewer().getLastName() : ""
                    ).trim() : null;
            String interviewerEmail = null;
            String locationName = cp.getLocation() != null ? cp.getLocation().getLocationName() : null;

            return CandidateProcessTimelineItemResponse.builder()
                    .id(cp.getCandProcessId())
                    .processDate(cp.getProcessDate())
                    .typeId(null)
                    .typeName(typeName)
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

    @Override
    public JobOfferResponse getJobOffer(Integer candidateId, Integer recruitProcessId) {
        // Get candidate info
        var candidate = candidatesRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found: " + candidateId));

        // Get recruitment process info
        var rpOpt = recruitmentProcessRepository.fetchRecruitmentInfoByProcessId(recruitProcessId);
        var rp = rpOpt.orElseThrow(() -> new RuntimeException("Recruitment process not found: " + recruitProcessId));

        // Get name from candidate (firstName + lastName)
        String name = String.format("%s %s",
                candidate.getFirstName() != null ? candidate.getFirstName() : "",
                candidate.getLastName() != null ? candidate.getLastName() : "").trim();

        // Get job title from recruitment
        String jobTitle = null;
        if (rp.getRecruitment() != null) {
            jobTitle = rp.getRecruitment().getTitle();
        }

        // Get department from division
        String department = null;
        if (rp.getRecruitment() != null && rp.getRecruitment().getDivision() != null) {
            department = rp.getRecruitment().getDivision().getDivisionName();
        }

        // Branch - for now set as default, can be enhanced later
        String branch = "Trụ sở chính";

        return JobOfferResponse.builder()
                .name(name)
                .jobTitle(jobTitle)
                .department(department)
                .branch(branch)
                .build();
    }

    @Override
    public OfferResponse createOffer(Integer candidateId, Integer recruitProcessId, CreateOfferRequest request) {
        // Validate candidate exists
        candidatesRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + candidateId));

        // Validate recruitment process exists
        recruitmentProcessRepository.findById(recruitProcessId)
                .orElseThrow(() -> new RuntimeException("Recruitment process not found with ID: " + recruitProcessId));

        // Create new offer
        Offers newOffer = Offers.builder()
                .onboardDate(request.getOnboardDate())
                .offerPosition(request.getOfferPosition())
                .basicSalary(request.getBasicSalary())
                .allowances(request.getAllowances())
                .bonuses(request.getBonuses())
                .totalFormalIncome(request.getTotalFormalIncome())
                .totalProbationaryIncome(request.getTotalProbationaryIncome())
                .jobTitleId(request.getJobTitleId())
                .status("Pending")  // Default status
                .build();

        Offers savedOffer = offersRepository.save(newOffer);

        return OfferResponse.builder()
                .offerId(savedOffer.getOfferId())
                .onboardDate(savedOffer.getOnboardDate())
                .offerPosition(savedOffer.getOfferPosition())
                .basicSalary(savedOffer.getBasicSalary())
                .allowances(savedOffer.getAllowances())
                .bonuses(savedOffer.getBonuses())
                .totalFormalIncome(savedOffer.getTotalFormalIncome())
                .totalProbationaryIncome(savedOffer.getTotalProbationaryIncome())
                .status(savedOffer.getStatus())
                .build();
    }

    @Override
    public OnboardingInfoResponse getOnboardingInfo(Integer candidateId, Integer recruitProcessId) {
        // Get candidate info
        var candidate = candidatesRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found: " + candidateId));

        // Get recruitment process info
        var rpOpt = recruitmentProcessRepository.fetchRecruitmentInfoByProcessId(recruitProcessId);
        var rp = rpOpt.orElseThrow(() -> new RuntimeException("Recruitment process not found: " + recruitProcessId));

        // Get name from candidate (firstName + lastName)
        String fullName = String.format("%s %s",
                candidate.getFirstName() != null ? candidate.getFirstName() : "",
                candidate.getLastName() != null ? candidate.getLastName() : "").trim();

        // Get job title from recruitment
        String jobTitle = null;
        if (rp.getRecruitment() != null) {
            jobTitle = rp.getRecruitment().getTitle();
        }

        // Get expected onboard date from offers (latest offer for this recruitment process)
        LocalDate expectedOnboardDate = null;
        // Note: We need to find the offer related to this recruitment process
        // For now, we'll get it from candidate's expected onboard date or set to null
        expectedOnboardDate = candidate.getExpectedOnboardDate();

        return OnboardingInfoResponse.builder()
                .fullName(fullName)
                .dateOfBirth(candidate.getDateOfBirth())
                .maritalStatus(candidate.getMaritalStatus())
                .expectedOnboardDate(expectedOnboardDate)
                .jobTitle(jobTitle)
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
        System.out.println("Mapping - ID: " + process.getRecruitProcessId() + ", Title: " + recruitmentTitle + ", Status: " + status + ", Code: " + code);
        return RecruitmentProcessResponse.builder()
                .id(process.getRecruitProcessId())
                .recruitmentTitle(recruitmentTitle)
                .status(status)
                .code(code)
                .position(position)
                .department(department)
                .build();
    }
}
