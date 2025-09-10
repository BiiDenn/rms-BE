package com.rms.recruitment.controllers;

import com.rms.recruitment.dto.*;
import com.rms.recruitment.dto.request.CandidateCreateRequest;
import com.rms.recruitment.dto.response.ApiResponse;
import com.rms.recruitment.services.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
@Tag(name = "Candidate Management", description = "Candidate management APIs")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    // Danh sách ứng viên với tìm kiếm và lọc
    // @PostMapping("/search")
    // @Operation(summary = "Tìm kiếm và lọc ứng viên", description = "Tìm kiếm ứng
    // viên theo các tiêu chí")
    // @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    // public ResponseEntity<ApiResponse<Page<CandidateDTO>>> searchCandidates(
    // @Valid @RequestBody CandidateSearchRequest searchRequest) {
    // try {
    // Page<CandidateDTO> candidates =
    // candidateService.searchCandidates(searchRequest);
    // return ResponseEntity.ok(ApiResponse.success(candidates, "Tìm kiếm ứng viên
    // thành công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi tìm kiếm ứng viên: " + e.getMessage()));
    // }
    // }

    @GetMapping
    @Operation(summary = "Lấy danh sách ứng viên", description = "Lấy danh sách ứng viên với phân trang")
    @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    public ResponseEntity<ApiResponse<Page<CandidateDTO>>> getAllCandidates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "candidateId,desc") String sort) {
        try {
            Page<CandidateDTO> candidates = candidateService.getAllCandidates(page, size, sort);
            return ResponseEntity.ok(ApiResponse.success(candidates, "Lấy danh sách ứng viên thành công"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Lỗi khi lấy danh sách ứng viên: " + e.getMessage()));
        }
    }

    // Tạo ứng viên mới
    @PostMapping
    @Operation(summary = "Tạo ứng viên mới", description = "Thêm ứng viên mới vào hệ thống")
    @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    public ResponseEntity<ApiResponse<CandidateDTO>> createCandidate(
            @Valid @RequestBody CandidateCreateRequest createRequest) {
        try {
            CandidateDTO candidate = candidateService.createCandidate(createRequest);
            return ResponseEntity.ok(ApiResponse.success(candidate, "Tạo ứng viên thành công"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Lỗi khi tạo ứng viên: " + e.getMessage()));
        }
    }

    // Chi tiết ứng viên
    @GetMapping("/{candidateId}")
    @Operation(summary = "Lấy chi tiết ứng viên", description = "Lấy thông tin chi tiết của ứng viên")
    @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    public ResponseEntity<ApiResponse<CandidateDTO>> getCandidateById(@PathVariable Integer candidateId) {
        try {
            CandidateDTO candidate = candidateService.getCandidateById(candidateId);
            return ResponseEntity.ok(ApiResponse.success(candidate, "Lấy chi tiết ứng viên thành công"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Lỗi khi lấy chi tiết ứng viên: " + e.getMessage()));
        }
    }

    @PutMapping("/{candidateId}")
    @Operation(summary = "Cập nhật thông tin ứng viên", description = "Cập nhật thông tin ứng viên")
    @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    public ResponseEntity<ApiResponse<CandidateDTO>> updateCandidate(
            @PathVariable Integer candidateId,
            @Valid @RequestBody CandidateCreateRequest updateRequest) {
        try {
            CandidateDTO candidate = candidateService.updateCandidate(candidateId, updateRequest);
            return ResponseEntity.ok(ApiResponse.success(candidate, "Cập nhật ứng viên thành công"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Lỗi khi cập nhật ứng viên: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{candidateId}")
    @Operation(summary = "Xóa ứng viên", description = "Xóa ứng viên khỏi hệ thống")
    @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    public ResponseEntity<ApiResponse<Void>> deleteCandidate(@PathVariable Integer candidateId) {
        try {
            candidateService.deleteCandidate(candidateId);
            return ResponseEntity.ok(ApiResponse.success(null, "Xóa ứng viên thành công"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Lỗi khi xóa ứng viên: " + e.getMessage()));
        }
    }

    // API quản lý CV/Resume
    // @PostMapping("/{candidateId}/resumes")
    // @Operation(summary = "Upload CV", description = "Upload CV cho ứng viên")
    // @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    // public ResponseEntity<ApiResponse<String>> uploadResume(
    // @PathVariable Integer candidateId,
    // @RequestParam("file") String fileName,
    // @RequestParam("fileUrl") String fileUrl) {
    // try {
    // candidateService.uploadResume(candidateId, fileName, fileUrl);
    // return ResponseEntity.ok(ApiResponse.success("Upload CV thành công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi upload CV: " + e.getMessage()));
    // }
    // }

    // @GetMapping("/{candidateId}/resumes")
    // @Operation(summary = "Lấy danh sách CV", description = "Lấy danh sách CV của
    // ứng viên")
    // @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    // public ResponseEntity<ApiResponse<List<ResumeDTO>>>
    // getCandidateResumes(@PathVariable Integer candidateId) {
    // try {
    // List<ResumeDTO> resumes = candidateService.getCandidateResumes(candidateId);
    // return ResponseEntity.ok(ApiResponse.success(resumes, "Lấy danh sách CV thành
    // công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi lấy danh sách CV: " + e.getMessage()));
    // }
    // }

    // API quản lý kỹ năng
    // @PostMapping("/{candidateId}/skills")
    // @Operation(summary = "Thêm kỹ năng", description = "Thêm kỹ năng cho ứng
    // viên")
    // @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    // public ResponseEntity<ApiResponse<String>> addSkill(
    // @PathVariable Integer candidateId,
    // @RequestParam String skillName,
    // @RequestParam String skillType) {
    // try {
    // candidateService.addSkill(candidateId, skillName, skillType);
    // return ResponseEntity.ok(ApiResponse.success("Thêm kỹ năng thành công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi thêm kỹ năng: " + e.getMessage()));
    // }
    // }

    // @GetMapping("/{candidateId}/skills")
    // @Operation(summary = "Lấy danh sách kỹ năng", description = "Lấy danh sách kỹ
    // năng của ứng viên")
    // @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    // public ResponseEntity<ApiResponse<List<String>>> getCandidateSkills(
    // @PathVariable Integer candidateId,
    // @RequestParam(required = false) String type) {
    // try {
    // List<String> skills = candidateService.getCandidateSkills(candidateId, type);
    // return ResponseEntity.ok(ApiResponse.success(skills, "Lấy danh sách kỹ năng
    // thành công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi lấy danh sách kỹ năng: " + e.getMessage()));
    // }
    // }

    // API lấy master data
    // @GetMapping("/master-data/genders")
    // @Operation(summary = "Lấy danh sách giới tính", description = "Lấy danh sách
    // giới tính")
    // public ResponseEntity<ApiResponse<List<String>>> getGenders() {
    // try {
    // List<String> genders = candidateService.getGenders();
    // return ResponseEntity.ok(ApiResponse.success(genders, "Lấy danh sách giới
    // tính thành công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi lấy danh sách giới tính: " +
    // e.getMessage()));
    // }
    // }

    // @GetMapping("/master-data/nationalities")
    // @Operation(summary = "Lấy danh sách quốc tịch", description = "Lấy danh sách
    // quốc tịch")
    // public ResponseEntity<ApiResponse<List<String>>> getNationalities() {
    // try {
    // List<String> nationalities = candidateService.getNationalities();
    // return ResponseEntity.ok(ApiResponse.success(nationalities, "Lấy danh sách
    // quốc tịch thành công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi lấy danh sách quốc tịch: " +
    // e.getMessage()));
    // }
    // }

    // @GetMapping("/master-data/recruitment-sources")
    // @Operation(summary = "Lấy danh sách nguồn tuyển dụng", description = "Lấy
    // danh sách nguồn tuyển dụng")
    // public ResponseEntity<ApiResponse<List<String>>> getRecruitmentSources() {
    // try {
    // List<String> sources = candidateService.getRecruitmentSources();
    // return ResponseEntity.ok(ApiResponse.success(sources, "Lấy danh sách nguồn
    // tuyển dụng thành công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi lấy danh sách nguồn tuyển dụng: " +
    // e.getMessage()));
    // }
    // }
}
