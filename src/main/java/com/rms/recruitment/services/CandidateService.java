package com.rms.recruitment.services;

import com.rms.recruitment.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CandidateService {

    // API cho Ảnh 1: Danh sách ứng viên
    Page<CandidateDTO> searchCandidates(CandidateSearchRequest searchRequest);

    Page<CandidateDTO> getAllCandidates(int page, int size, String sort);

    // API cho Ảnh 2: Tạo ứng viên mới
    CandidateDTO createCandidate(CandidateCreateRequest createRequest);

    // API cho Ảnh 3: Chi tiết ứng viên
    CandidateDTO getCandidateById(Integer candidateId);

    CandidateDTO updateCandidate(Integer candidateId, CandidateCreateRequest updateRequest);

    void deleteCandidate(Integer candidateId);

    // API quản lý CV/Resume
    void uploadResume(Integer candidateId, String fileName, String fileUrl);

    List<ResumeDTO> getCandidateResumes(Integer candidateId);

    // API quản lý kỹ năng
    void addSkill(Integer candidateId, String skillName, String skillType);

    List<String> getCandidateSkills(Integer candidateId, String type);

    // API lấy master data
    List<String> getGenders();

    List<String> getNationalities();

    List<String> getRecruitmentSources();
}
