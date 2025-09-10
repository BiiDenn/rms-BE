package com.rms.recruitment.services.Impl;

import com.rms.recruitment.dto.*;
import com.rms.recruitment.dto.request.CandidateCreateRequest;
import com.rms.recruitment.dto.request.CandidateSearchRequest;
import com.rms.recruitment.models.Candidates;
import com.rms.recruitment.models.CandidateProcess;
import com.rms.recruitment.repositories.CandidatesRepository;
import com.rms.recruitment.repositories.CandidateProcessRepository;
import com.rms.recruitment.services.CandidateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidatesRepository candidatesRepository;
    private final CandidateProcessRepository candidateProcessRepository;

    public CandidateServiceImpl(CandidatesRepository candidatesRepository,
            CandidateProcessRepository candidateProcessRepository) {
        this.candidatesRepository = candidatesRepository;
        this.candidateProcessRepository = candidateProcessRepository;
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

        Page<Candidates> candidatesPage = candidatesRepository.findAll(pageable);

        return candidatesPage.map(this::convertToDTO);
    }

    @Override
    public Page<CandidateListDTO> getAllCandidates(int page, int size, String sort) {
        Sort sortObj = Sort.by(Sort.Direction.DESC, "candId");
        if (sort != null && !sort.isEmpty()) {
            String[] sortParams = sort.split(",");
            if (sortParams.length == 2) {
                sortObj = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
            }
        }

        Pageable pageable = PageRequest.of(page, size, sortObj);
        Page<Candidates> candidatesPage = candidatesRepository.findAll(pageable);

        return candidatesPage.map(this::convertToListDTO);
    }

    @Override
    public CandidateDTO createCandidate(CandidateCreateRequest createRequest) {
        // Tạo entity từ request
        Candidates candidate = new Candidates();
        candidate.setFirstName(createRequest.getFirstName());
        candidate.setLastName(createRequest.getLastName());
        candidate.setDateOfBirth(createRequest.getDateOfBirth());

        if (createRequest.getGender() != null) {
            candidate.setGender(createRequest.getGender().equals("Nam") ? 1 : 0);
        }

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

            if (updateRequest.getGender() != null) {
                candidate.setGender(updateRequest.getGender().equals("Nam") ? 1 : 0);
            }

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

    private CandidateDTO convertToDTO(Candidates candidate) {
        return CandidateDTO.builder()
                .candidateId(candidate.getCandId())
                .candidateCode("UV" + candidate.getCandId() + "-" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM.yyyy")))
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .fullName(candidate.getFirstName() + " " + candidate.getLastName())
                .dateOfBirth(candidate.getDateOfBirth())
                .gender(getGenderName(candidate))
                .nationality(candidate.getNationality())
                .mainEmail(candidate.getMainEmail())
                .subEmail(candidate.getSubEmail())
                .mainPhone(candidate.getMainPhone())
                .subPhone(candidate.getSubPhone())

                // hard code
                .primaryLanguage("Tiếng Việt")
                .secondaryLanguage("Tiếng Anh")
                .recruitmentSource(getRecruitmentSource(candidate))
                .referrer(candidate.getReferredBy())
                .maritalStatus(candidate.getMaritalStatus())
                .address("Chưa cập nhật")
                .qualifications(candidate.getNote())
                .hardSkills(splitComma(candidate.getHardSkill(), "Chưa cập nhật"))
                .softSkills(splitComma(candidate.getSoftSkill(), "Chưa cập nhật"))
                .resumes(new ArrayList<>())
                .lastUpdatedBy("System")
                .lastUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy")))
                .recruitmentId(candidate.getJobTitleId())
                .recruitmentCode(getRecruitmentCode(candidate))
                .build();
    }

    private java.util.List<String> splitComma(String value, String fallbackIfEmpty) {
        if (value == null || value.trim().isEmpty()) {
            return java.util.Arrays.asList(fallbackIfEmpty);
        }
        return java.util.Arrays.stream(value.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }

    private CandidateListDTO convertToListDTO(Candidates candidate) {
        return CandidateListDTO.builder()
                .candidateId(candidate.getCandId())
                .candidateCode("UV" + candidate.getCandId() + "-" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM.yyyy")))
                .fullName(candidate.getFirstName() + " " + candidate.getLastName())
                .recruitmentSource(getRecruitmentSource(candidate))
                .recruitmentCode(getRecruitmentCode(candidate))
                .progress(getProgress(candidate))
                .status(getStatus(candidate))
                .build();
    }

    private String getRecruitmentSource(Candidates candidate) {
        // Lấy trực tiếp từ trường recruitmentSource (nguồn ứng tuyển nhập tay)
        if (candidate.getRecruitmentSource() != null && !candidate.getRecruitmentSource().isEmpty()) {
            return candidate.getRecruitmentSource();
        }

        return "Chưa xác định";
    }

    private String getRecruitmentCode(Candidates candidate) {
        // Lấy từ jobTitle -> recruitment
        if (candidate.getJobTitle() != null && candidate.getJobTitle().getRecruitment() != null) {
            return candidate.getJobTitle().getRecruitment().getRecruitmentCode();
        }

        // Nếu không có recruitment, tạo từ jobTitle
        if (candidate.getJobTitle() != null) {
            return candidate.getJobTitle().getPosition() + "_" + candidate.getJobTitle().getLevel();
        }

        return "Chưa xác định";
    }

    public CandidatesRepository getCandidatesRepository() {
        return candidatesRepository;
    }

    public CandidateProcessRepository getCandidateProcessRepository() {
        return candidateProcessRepository;
    }

    private String getProgress(Candidates candidate) {
        // Lấy process mới nhất từ candidate_process
        Optional<CandidateProcess> latestProcess = candidateProcessRepository.findLatestByCandId(candidate.getCandId());

        if (latestProcess.isPresent()) {
            return latestProcess.get().getCandProcessName();
        }

        return "Chưa có tiến độ"; // Mặc định nếu chưa có process
    }

    private String getStatus(Candidates candidate) {
        // Lấy status mới nhất từ candidate_process
        Optional<CandidateProcess> latestProcess = candidateProcessRepository.findLatestByCandId(candidate.getCandId());

        if (latestProcess.isPresent()) {
            return latestProcess.get().getStatus();
        }

        return "Chưa có trạng thái"; // Mặc định nếu chưa có process
    }

    private String getGenderName(Candidates candidate) {
        // Lấy từ MasterData thông qua genderMasterData
        if (candidate.getGenderMasterData() != null) {
            return candidate.getGenderMasterData().getName();
        }

        // Fallback nếu không có MasterData
        if (candidate.getGender() != null) {
            return candidate.getGender() == 1 ? "Nam" : "Nữ";
        }

        return "Chưa xác định";
    }
}