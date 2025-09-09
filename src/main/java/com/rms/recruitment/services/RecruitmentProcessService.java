package com.rms.recruitment.services;

import com.rms.recruitment.dto.CreateRecruitmentProcessRequest;
import com.rms.recruitment.dto.RecruitmentProcessResponse;

import java.util.List;

public interface RecruitmentProcessService {
    List<RecruitmentProcessResponse> getRecruitmentProcessesByCandidateId(Integer candidateId);
    RecruitmentProcessResponse createRecruitmentProcess(Integer candidateId, CreateRecruitmentProcessRequest request);
}
