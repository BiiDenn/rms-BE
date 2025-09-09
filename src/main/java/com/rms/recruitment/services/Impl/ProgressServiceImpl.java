package com.rms.recruitment.services.Impl;

import com.rms.recruitment.dto.*;
import com.rms.recruitment.services.ProgressService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    @Override
    public CandidateProgressDTO getCandidateProgress(Integer candidateId) {
        // Tạm thời trả về dữ liệu mẫu
        List<ApplicationDTO> applications = new ArrayList<>();

        // Application 1 - Đang thực hiện
        List<ProgressStepDTO> steps1 = new ArrayList<>();
        steps1.add(ProgressStepDTO.builder()
                .stepId(1)
                .stepName("Thêm mới")
                .stepDate(LocalDate.of(2025, 8, 11))
                .executor("Tên người thực hiện")
                .status("COMPLETED")
                .actions(Arrays.asList("Thêm CV", "Nút tải CV lên"))
                .description("Ứng viên được thêm vào hệ thống")
                .build());

        steps1.add(ProgressStepDTO.builder()
                .stepId(2)
                .stepName("Sàn lọc")
                .stepDate(LocalDate.of(2025, 8, 12))
                .executor("Tên người thực hiện")
                .status("PASSED")
                .actions(Arrays.asList("Xem chi tiết"))
                .description("Ứng viên đã vượt qua vòng sàng lọc")
                .build());

        applications.add(ApplicationDTO.builder()
                .applicationId(1)
                .title("Business Analyst")
                .code("BA_Mid.ITPS.01/2025")
                .department("IT Department")
                .status("IN_PROGRESS")
                .daysRemaining(0)
                .progressSteps(steps1)
                .build());

        // Application 2 - Kết thúc
        List<ProgressStepDTO> steps2 = new ArrayList<>();
        steps2.add(ProgressStepDTO.builder()
                .stepId(3)
                .stepName("Phỏng vấn 1")
                .stepDate(LocalDate.of(2025, 8, 15))
                .executor("Interviewer 1")
                .status("COMPLETED")
                .actions(Arrays.asList("Xem kết quả"))
                .description("Hoàn thành vòng phỏng vấn đầu tiên")
                .build());

        applications.add(ApplicationDTO.builder()
                .applicationId(2)
                .title("Software Developer")
                .code("DEV_Junior.ITPS.02/2025")
                .department("IT Department")
                .status("FINISHED")
                .daysRemaining(0)
                .progressSteps(steps2)
                .build());

        return CandidateProgressDTO.builder()
                .candidateId(candidateId)
                .candidateName("Hà Huỳnh Ánh Ngân")
                .applications(applications)
                .build();
    }

    @Override
    public void addProgressStep(Integer candidateId, ProgressStepCreateRequest createRequest) {
        // Tạm thời chỉ log, sau này sẽ implement logic thêm bước tiến độ
        System.out.println("Add progress step for candidate " + candidateId + ": " + createRequest.getStepName());
    }

    @Override
    public void addApplication(Integer candidateId, Integer recruitmentId, String position, String department) {
        // Tạm thời chỉ log, sau này sẽ implement logic thêm đơn ứng tuyển
        System.out.println("Add application for candidate " + candidateId + ": " + position + " at " + department);
    }

    @Override
    public void updateProgressStatus(Integer candidateId, Integer stepId, String status, String note) {
        // Tạm thời chỉ log, sau này sẽ implement logic cập nhật trạng thái
        System.out.println("Update progress status for candidate " + candidateId + ", step " + stepId + ": " + status);
    }

    @Override
    public ProgressStepDTO getProgressStepDetail(Integer candidateId, Integer stepId) {
        // Tạm thời trả về dữ liệu mẫu
        return ProgressStepDTO.builder()
                .stepId(stepId)
                .stepName("Sàn lọc")
                .stepDate(LocalDate.of(2025, 8, 12))
                .executor("Tên người thực hiện")
                .status("PASSED")
                .actions(Arrays.asList("Xem chi tiết", "Tải file đính kèm"))
                .description("Ứng viên đã vượt qua vòng sàng lọc với điểm số cao")
                .build();
    }

    @Override
    public List<String> getProgressStatuses() {
        return Arrays.asList("COMPLETED", "IN_PROGRESS", "PASSED", "FAILED", "CANCELLED");
    }

    @Override
    public List<String> getProgressStepTypes() {
        return Arrays.asList("Thêm mới", "Sàn lọc", "Phỏng vấn 1", "Phỏng vấn 2", "Đánh giá", "Offer", "Onboarding");
    }
}
