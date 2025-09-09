package com.rms.recruitment.services.Impl;

import com.rms.recruitment.dto.CreateRecruitmentProcessRequest;
import com.rms.recruitment.dto.RecruitmentProcessResponse;
import com.rms.recruitment.models.CandidateProcess;
import com.rms.recruitment.models.RecruitmentProcess;
import com.rms.recruitment.repositories.CandidateProcessRepository;
import com.rms.recruitment.repositories.CandidatesRepository;
import com.rms.recruitment.repositories.RecruitmentProcessRepository;
import com.rms.recruitment.services.RecruitmentProcessService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        System.out.println("Recruit ID: " + request.getRecruitId());
        System.out.println("Status: " + request.getRecruitProcessStatus());
        
        // Validate candidate exists
        candidatesRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + candidateId));

        // Create new recruitment process
        RecruitmentProcess newProcess = RecruitmentProcess.builder()
                .recruitId(request.getRecruitId())
                .recruitProcessStatus(request.getRecruitProcessStatus() != null ? 
                    request.getRecruitProcessStatus() : "In Progress")
                .createdAt(LocalDateTime.now())
                .build();

        RecruitmentProcess savedProcess = recruitmentProcessRepository.save(newProcess);
        System.out.println("Saved Process ID: " + savedProcess.getRecruitProcessId());

        // Fetch the saved process with relationships
        RecruitmentProcess processWithRelations = recruitmentProcessRepository.findByIdWithRelations(savedProcess.getRecruitProcessId())
                .orElseThrow(() -> new RuntimeException("Failed to fetch created process"));
        
        // Alternative: Try fetching by recruitId to test
        System.out.println("=== TESTING ALTERNATIVE FETCH ===");
        List<RecruitmentProcess> processesByRecruitId = recruitmentProcessRepository.findByRecruitIdWithRelations(request.getRecruitId());
        System.out.println("Found " + processesByRecruitId.size() + " processes for recruitId: " + request.getRecruitId());
        for (RecruitmentProcess p : processesByRecruitId) {
            System.out.println("Process ID: " + p.getRecruitProcessId() + ", Recruitment: " + 
                (p.getRecruitment() != null ? p.getRecruitment().getTitle() : "NULL"));
        }

        // Use a process that has recruitment data instead of the newly created one
        RecruitmentProcess processToReturn = processWithRelations;
        if (processWithRelations.getRecruitment() == null && !processesByRecruitId.isEmpty()) {
            // Find a process with recruitment data
            for (RecruitmentProcess p : processesByRecruitId) {
                if (p.getRecruitment() != null) {
                    processToReturn = p;
                    System.out.println("Using process ID: " + p.getRecruitProcessId() + " with recruitment data");
                    break;
                }
            }
        }

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

        // Create candidate process link to connect candidate with the new recruitment process
        System.out.println("=== CREATING CANDIDATE PROCESS LINK ===");
        System.out.println("Creating link: Candidate " + candidateId + " -> Process " + savedProcess.getRecruitProcessId());
        
        CandidateProcess candidateProcess = CandidateProcess.builder()
                .candId(candidateId)
                .recruitProcessId(savedProcess.getRecruitProcessId())
                .processDate(java.time.LocalDate.now())
                .status("Active")
                .candidateProcessType("Application Submitted")
                .build();
        
        CandidateProcess savedCandidateProcess = candidateProcessRepository.save(candidateProcess);
        System.out.println("CandidateProcess saved: ID=" + savedCandidateProcess.getCandProcessId() + 
                          ", Candidate=" + savedCandidateProcess.getCandId() + 
                          ", Process=" + savedCandidateProcess.getRecruitProcessId());

        return mapToResponse(processToReturn);
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
