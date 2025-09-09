package com.rms.recruitment.services.Impl;

import com.rms.recruitment.dto.*;
import com.rms.recruitment.models.Candidates;
import com.rms.recruitment.repositories.CandidatesRepository;
import com.rms.recruitment.services.CandidateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidatesRepository candidatesRepository;

    public CandidateServiceImpl(CandidatesRepository candidatesRepository) {
        this.candidatesRepository = candidatesRepository;
    }

    @Override
    public Page<CandidateDTO> searchCandidates(CandidateSearchRequest searchRequest) {
        // Tạo Pageable object
        Sort sort = Sort.by(Sort.Direction.DESC, "candId");
        if (searchRequest.getSort() != null && !searchRequest.getSort().isEmpty()) {
            String[] sortParams = searchRequest.getSort().split(",");
            if (sortParams.length == 2) {
                sort = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
            }
        }

        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getSize(), sort);

        // Tạm thời trả về tất cả candidates, sau này sẽ implement search logic
        Page<Candidates> candidatesPage = candidatesRepository.findAll(pageable);

        return candidatesPage.map(this::convertToDTO);
    }

    @Override
    public Page<CandidateDTO> getAllCandidates(int page, int size, String sort) {
        Sort sortObj = Sort.by(Sort.Direction.DESC, "candId");
        if (sort != null && !sort.isEmpty()) {
            String[] sortParams = sort.split(",");
            if (sortParams.length == 2) {
                sortObj = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
            }
        }

        Pageable pageable = PageRequest.of(page, size, sortObj);
        Page<Candidates> candidatesPage = candidatesRepository.findAll(pageable);

        return candidatesPage.map(this::convertToDTO);
    }

    @Override
    public CandidateDTO createCandidate(CandidateCreateRequest createRequest) {
        // Tạo entity từ request
        Candidates candidate = new Candidates();
        candidate.setFirstName(createRequest.getFirstName());
        candidate.setLastName(createRequest.getLastName());
        candidate.setDateOfBirth(createRequest.getDateOfBirth());
        candidate.setGender(createRequest.getGender() != null ? 1 : 0); // Tạm thời
        candidate.setMainEmail(createRequest.getMainEmail());
        candidate.setSubEmail(createRequest.getSubEmail());
        candidate.setMainPhone(createRequest.getMainPhone());
        candidate.setSubPhone(createRequest.getSubPhone());
        candidate.setNationality(createRequest.getNationality());
        candidate.setReferredBy(createRequest.getReferrer());
        candidate.setNote(createRequest.getQualifications());
        candidate.setJobTitleId(createRequest.getRecruitmentId());

        // Lưu vào database
        Candidates savedCandidate = candidatesRepository.save(candidate);

        return convertToDTO(savedCandidate);
    }

    @Override
    public CandidateDTO getCandidateById(Integer candidateId) {
        Optional<Candidates> candidateOpt = candidatesRepository.findById(candidateId);
        if (candidateOpt.isPresent()) {
            return convertToDTO(candidateOpt.get());
        }
        throw new RuntimeException("Không tìm thấy ứng viên với ID: " + candidateId);
    }

    @Override
    public CandidateDTO updateCandidate(Integer candidateId, CandidateCreateRequest updateRequest) {
        Optional<Candidates> candidateOpt = candidatesRepository.findById(candidateId);
        if (candidateOpt.isPresent()) {
            Candidates candidate = candidateOpt.get();

            // Cập nhật thông tin
            candidate.setFirstName(updateRequest.getFirstName());
            candidate.setLastName(updateRequest.getLastName());
            candidate.setDateOfBirth(updateRequest.getDateOfBirth());
            candidate.setMainEmail(updateRequest.getMainEmail());
            candidate.setSubEmail(updateRequest.getSubEmail());
            candidate.setMainPhone(updateRequest.getMainPhone());
            candidate.setSubPhone(updateRequest.getSubPhone());
            candidate.setNationality(updateRequest.getNationality());
            candidate.setReferredBy(updateRequest.getReferrer());
            candidate.setNote(updateRequest.getQualifications());

            Candidates updatedCandidate = candidatesRepository.save(candidate);
            return convertToDTO(updatedCandidate);
        }
        throw new RuntimeException("Không tìm thấy ứng viên với ID: " + candidateId);
    }

    @Override
    public void deleteCandidate(Integer candidateId) {
        if (candidatesRepository.existsById(candidateId)) {
            candidatesRepository.deleteById(candidateId);
        } else {
            throw new RuntimeException("Không tìm thấy ứng viên với ID: " + candidateId);
        }
    }

    @Override
    public void uploadResume(Integer candidateId, String fileName, String fileUrl) {
        // Tạm thời chỉ log, sau này sẽ implement upload logic
        System.out.println("Upload resume for candidate " + candidateId + ": " + fileName);
    }

    @Override
    public List<ResumeDTO> getCandidateResumes(Integer candidateId) {
        // Tạm thời trả về dữ liệu mẫu
        List<ResumeDTO> resumes = new ArrayList<>();
        resumes.add(ResumeDTO.builder()
                .resumeId(1)
                .fileName("Resume_2025.pdf")
                .fileUrl("/files/resume_2025.pdf")
                .uploadDate("2025-04-12")
                .description("CV mới nhất")
                .build());
        resumes.add(ResumeDTO.builder()
                .resumeId(2)
                .fileName("Resume_2024.pdf")
                .fileUrl("/files/resume_2024.pdf")
                .uploadDate("2024-12-01")
                .description("CV cũ")
                .build());
        return resumes;
    }

    @Override
    public void addSkill(Integer candidateId, String skillName, String skillType) {
        // Tạm thời chỉ log, sau này sẽ implement skill management
        System.out.println("Add skill for candidate " + candidateId + ": " + skillName + " (" + skillType + ")");
    }

    @Override
    public List<String> getCandidateSkills(Integer candidateId, String type) {
        // Tạm thời trả về dữ liệu mẫu
        if ("HARD".equals(type)) {
            return Arrays.asList("Java", "Spring Boot", "MySQL", "React");
        } else if ("SOFT".equals(type)) {
            return Arrays.asList("Giao tiếp", "Làm việc nhóm", "Giải quyết vấn đề");
        } else {
            return Arrays.asList("Java", "Spring Boot", "MySQL", "React", "Giao tiếp", "Làm việc nhóm");
        }
    }

    @Override
    public List<String> getGenders() {
        return Arrays.asList("Nam", "Nữ", "Khác");
    }

    @Override
    public List<String> getNationalities() {
        return Arrays.asList("Việt Nam", "Hàn Quốc", "Nhật Bản", "Trung Quốc", "Mỹ", "Anh");
    }

    @Override
    public List<String> getRecruitmentSources() {
        return Arrays.asList("TopDev", "ITviec", "Skype", "Nội bộ", "Facebook", "LinkedIn");
    }

    private CandidateDTO convertToDTO(Candidates candidate) {
        return CandidateDTO.builder()
                .candidateId(candidate.getCandId())
                .candidateCode("UV" + candidate.getCandId() + "-" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM.yyyy")))
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .fullName(candidate.getFirstName() + " " + candidate.getLastName())
                .dateOfBirth(candidate.getDateOfBirth())
                .gender(candidate.getGender() != null && candidate.getGender() == 1 ? "Nam" : "Nữ")
                .nationality(candidate.getNationality())
                .mainEmail(candidate.getMainEmail())
                .subEmail(candidate.getSubEmail())
                .mainPhone(candidate.getMainPhone())
                .subPhone(candidate.getSubPhone())
                .primaryLanguage("Tiếng Việt")
                .secondaryLanguage("Tiếng Anh")
                .recruitmentSource("TopDev")
                .referrer(candidate.getReferredBy())
                .maritalStatus("Độc thân")
                .address("374 Lê Văn Toán phường Tân Hiệp Quận Nhà Bè")
                .qualifications(candidate.getNote())
                .hardSkills(Arrays.asList("Java", "Spring Boot", "MySQL"))
                .softSkills(Arrays.asList("Giao tiếp", "Làm việc nhóm"))
                .resumes(getCandidateResumes(candidate.getCandId()))
                .lastUpdatedBy("HR1")
                .lastUpdatedAt("12:00:00 12/04/2025")
                .recruitmentId(candidate.getJobTitleId())
                .recruitmentCode("BA_Mid.ITPS.01/2025")
                .build();
    }
}
