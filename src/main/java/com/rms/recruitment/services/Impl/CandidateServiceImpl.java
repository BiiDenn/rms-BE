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
import java.util.Arrays;
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
            candidate.setGender(parseGender(createRequest.getGender()));
        }

        candidate.setMainEmail(createRequest.getMainEmail());
        candidate.setSubEmail(createRequest.getSubEmail());
        candidate.setMainPhone(createRequest.getMainPhone());
        candidate.setSubPhone(createRequest.getSubPhone());
        candidate.setNationality(createRequest.getNationality());
        candidate.setPrimaryLanguage(createRequest.getPrimaryLanguage());
        candidate.setSecondaryLanguage(createRequest.getSecondaryLanguage());
        candidate.setRecruitmentSource(createRequest.getRecruitmentSource());
        candidate.setReferredBy(createRequest.getReferrer());
        candidate.setMaritalStatus(createRequest.getMaritalStatus());
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
                candidate.setGender(parseGender(updateRequest.getGender()));
            }

            candidate.setMainEmail(updateRequest.getMainEmail());
            candidate.setSubEmail(updateRequest.getSubEmail());
            candidate.setMainPhone(updateRequest.getMainPhone());
            candidate.setSubPhone(updateRequest.getSubPhone());
            candidate.setNationality(updateRequest.getNationality());
            candidate.setPrimaryLanguage(updateRequest.getPrimaryLanguage());
            candidate.setSecondaryLanguage(updateRequest.getSecondaryLanguage());
            candidate.setRecruitmentSource(updateRequest.getRecruitmentSource());
            candidate.setReferredBy(updateRequest.getReferrer());
            candidate.setMaritalStatus(updateRequest.getMaritalStatus());
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

                .primaryLanguage(candidate.getPrimaryLanguage())
                .secondaryLanguage(candidate.getSecondaryLanguage())
                .recruitmentSource(getRecruitmentSource(candidate))
                .referrer(candidate.getReferredBy())
                .maritalStatus(candidate.getMaritalStatus())
                .qualifications(candidate.getNote())
                .recruitmentId(candidate.getJobTitleId())
                .recruitmentCode(getRecruitmentCode(candidate))
                .build();
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
        // Lấy từ trường referredBy
        if (candidate.getReferredBy() != null && !candidate.getReferredBy().isEmpty()) {
            return candidate.getReferredBy();
        }

        return null;
    }

    private String getRecruitmentCode(Candidates candidate) {
        // Lấy từ jobTitle -> recruitment
        if (candidate.getJobTitle() != null && candidate.getJobTitle().getRecruitment() != null) {
            return candidate.getJobTitle().getRecruitment().getRecruitmentCode();
        }

        // Không tự sinh nếu không có liên kết dữ liệu thật
        return null;
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

        return null;
    }

    private String getStatus(Candidates candidate) {
        // Lấy status mới nhất từ candidate_process
        Optional<CandidateProcess> latestProcess = candidateProcessRepository.findLatestByCandId(candidate.getCandId());

        if (latestProcess.isPresent()) {
            return latestProcess.get().getStatus();
        }

        return null;
    }

    private String getGenderName(Candidates candidate) {
        // Lấy từ trường gender trực tiếp
        if (candidate.getGender() != null) {
            return candidate.getGender() == 1 ? "Nam" : candidate.getGender() == 0 ? "Nữ" : null;
        }

        return null;

    }

    private Integer parseGender(String genderInput) {
        String normalized = genderInput.trim().toLowerCase();
        if (normalized.equals("1") || normalized.equals("nam") || normalized.equals("male") || normalized.equals("m")) {
            return 1;
        }
        if (normalized.equals("0") || normalized.equals("nữ") || normalized.equals("nu") || normalized.equals("female")
                || normalized.equals("f")) {
            return 0;
        }
        try {
            return Integer.parseInt(normalized);
        } catch (NumberFormatException ignored) {
        }
        return null;
    }
}
