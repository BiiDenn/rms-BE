package com.rms.recruitment.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
@Tag(name = "Candidate Progress Management", description = "Candidate progress management APIs")
public class ProgressController {

    // private final ProgressService progressService;

    // public ProgressController(ProgressService progressService) {
    // this.progressService = progressService;
    // }

    // Lấy tiến độ ứng viên

    // @GetMapping("/{candidateId}/progress")

    // @Operation(summary = "Lấy tiến độ ứng viên", description = "Lấy tiến độ của
    // ứng viên trong quy trình tuyển dụng")

    // @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    // public ResponseEntity<ApiResponse<CandidateProgressDTO>>
    // getCandidateProgress(

    // @PathVariable Integer candidateId) {
    // try {
    // CandidateProgressDTO progress =
    // progressService.getCandidateProgress(candidateId);
    // return ResponseEntity.ok(ApiResponse.success(progress,
    // "Lấy tiến độ ứng viên thành công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi lấy tiến độ ứng viên: " + e.getMessage()));
    // }
    // }

    // // API thêm tiến độ mới

    // @PostMapping("/{candidateId}/progress")

    // @Operation(summary = "Thêm tiến độ mới", description = "Thêm bước tiến độ mới
    // cho ứng viên")

    // @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    // public ResponseEntity<ApiResponse<String>> addProgressStep(

    // @PathVariable Integer candidateId,

    // @Valid @RequestBody ProgressStepCreateRequest createRequest) {
    // try {
    // progressService.addProgressStep(candidateId, createRequest);
    // return ResponseEntity.ok(ApiResponse.success("Thêm tiến độ thành công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi thêm tiến độ: " + e.getMessage()));
    // }
    // }

    // // API thêm đơn ứng tuyển mới

    // @PostMapping("/{candidateId}/applications")

    // @Operation(summary = "Thêm đơn ứng tuyển mới", description = "Thêm đơn ứng
    // tuyển mới cho ứng viên")

    // @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    // public ResponseEntity<ApiResponse<String>> addApplication(

    // @PathVariable Integer candidateId,

    // @RequestParam Integer recruitmentId,

    // @RequestParam String position,

    // @RequestParam String department) {
    // try {
    // progressService.addApplication(candidateId, recruitmentId, position,
    // department);
    // return ResponseEntity.ok(ApiResponse.success("Thêm đơn ứng tuyển thành
    // công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi thêm đơn ứng tuyển: " + e.getMessage()));
    // }
    // }

    // // API cập nhật trạng thái tiến độ

    // @PutMapping("/{candidateId}/progress/{stepId}/status")

    // @Operation(summary = "Cập nhật trạng thái tiến độ", description = "Cập nhật
    // trạng thái của bước tiến độ")

    // @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    // public ResponseEntity<ApiResponse<String>> updateProgressStatus(

    // @PathVariable Integer candidateId,

    // @PathVariable Integer stepId,

    // @RequestParam String status,

    // @RequestParam(required = false) String note) {
    // try {
    // progressService.updateProgressStatus(candidateId, stepId, status, note);
    // return ResponseEntity.ok(ApiResponse.success("Cập nhật trạng thái thành
    // công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi cập nhật trạng thái: " + e.getMessage()));
    // }
    // }

    // // API xem chi tiết bước tiến độ

    // @GetMapping("/{candidateId}/progress/{stepId}")

    // @Operation(summary = "Xem chi tiết bước tiến độ", description = "Xem chi tiết
    // của bước tiến độ")

    // @PreAuthorize("hasAuthority('MANAGE_CANDIDATES')")
    // public ResponseEntity<ApiResponse<ProgressStepDTO>> getProgressStepDetail(

    // @PathVariable Integer candidateId,

    // @PathVariable Integer stepId) {
    // try {
    // ProgressStepDTO stepDetail =
    // progressService.getProgressStepDetail(candidateId, stepId);
    // return ResponseEntity.ok(ApiResponse.success(stepDetail,
    // "Lấy chi tiết bước tiến độ thành công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi lấy chi tiết bước tiến độ: " +
    // e.getMessage()));
    // }
    // }

    // // API lấy danh sách trạng thái tiến độ

    // @GetMapping("/progress/statuses")

    // @Operation(summary = "Lấy danh sách trạng thái", description = "Lấy danh sách
    // các trạng thái tiến độ")
    // public ResponseEntity<ApiResponse<List<String>>> getProgressStatuses() {
    // try {
    // List<String> statuses = progressService.getProgressStatuses();
    // return ResponseEntity.ok(ApiResponse.success(statuses,
    // "Lấy danh sách trạng thái thành công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi lấy danh sách trạng thái: " +
    // e.getMessage()));
    // }
    // }

    // // API lấy danh sách loại tiến độ

    // @GetMapping("/progress/step-types")

    // @Operation(summary = "Lấy danh sách loại tiến độ", description = "Lấy danh
    // sách các loại bước tiến độ")
    // public ResponseEntity<ApiResponse<List<String>>> getProgressStepTypes() {
    // try {
    // List<String> stepTypes = progressService.getProgressStepTypes();
    // return ResponseEntity.ok(ApiResponse.success(stepTypes,
    // "Lấy danh sách loại tiến độ thành công"));
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body(ApiResponse.error("Lỗi khi lấy danh sách loại tiến độ: " +
    // e.getMessage()));
    // }
    // }
}
