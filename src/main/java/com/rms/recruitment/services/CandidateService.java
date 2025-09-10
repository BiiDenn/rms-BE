package com.rms.recruitment.services;

import com.rms.recruitment.dto.*;
import com.rms.recruitment.dto.request.CandidateCreateRequest;
import com.rms.recruitment.dto.request.CandidateSearchRequest;
import org.springframework.data.domain.Page;

public interface CandidateService {

    // Danh sách ứng viên
    Page<CandidateDTO> searchCandidates(CandidateSearchRequest searchRequest);

    Page<CandidateListDTO> getAllCandidates(int page, int size, String sort);

    // Tạo ứng viên mới
    CandidateDTO createCandidate(CandidateCreateRequest createRequest);

    // Chi tiết ứng viên
    CandidateDTO getCandidateById(Integer candidateId);

    CandidateDTO updateCandidate(Integer candidateId, CandidateCreateRequest updateRequest);

    void deleteCandidate(Integer candidateId);
}
