package com.rms.recruitment.services;

import com.rms.recruitment.dto.CandidateProcessTimelineItemResponse;
import com.rms.recruitment.dto.CreateRecruitmentProcessRequest;
import com.rms.recruitment.dto.RecruitmentProcessResponse;
import com.rms.recruitment.dto.InterviewOverviewResponse;
import com.rms.recruitment.dto.JobOfferResponse;
import com.rms.recruitment.dto.CreateOfferRequest;
import com.rms.recruitment.dto.OfferResponse;
import com.rms.recruitment.dto.OnboardingInfoResponse;

import java.util.List;

public interface RecruitmentProcessService {
    List<RecruitmentProcessResponse> getRecruitmentProcessesByCandidateId(Integer candidateId);
    RecruitmentProcessResponse createRecruitmentProcess(Integer candidateId, CreateRecruitmentProcessRequest request);

    List<CandidateProcessTimelineItemResponse> getCandidateProcessTimeline(Integer candidateId, Integer recruitProcessId);

    InterviewOverviewResponse getInterviewOverview(Integer candidateId, Integer recruitProcessId);

    JobOfferResponse getJobOffer(Integer candidateId, Integer recruitProcessId);

    OfferResponse createOffer(Integer candidateId, Integer recruitProcessId, CreateOfferRequest request);

    OnboardingInfoResponse getOnboardingInfo(Integer candidateId, Integer recruitProcessId);
}
