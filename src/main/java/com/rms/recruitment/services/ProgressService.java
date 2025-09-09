package com.rms.recruitment.services;

import com.rms.recruitment.dto.*;

import java.util.List;

public interface ProgressService {

    // API cho Ảnh 4: Tiến độ ứng viên
    CandidateProgressDTO getCandidateProgress(Integer candidateId);

    void addProgressStep(Integer candidateId, ProgressStepCreateRequest createRequest);

    void addApplication(Integer candidateId, Integer recruitmentId, String position, String department);

    void updateProgressStatus(Integer candidateId, Integer stepId, String status, String note);

    ProgressStepDTO getProgressStepDetail(Integer candidateId, Integer stepId);

    // API hỗ trợ
    List<String> getProgressStatuses();

    List<String> getProgressStepTypes();
}
