package com.rms.recruitment.enums;

public enum PermissionType {
    MANAGE_ACCOUNTS("MANAGE_ACCOUNTS", "Quản lý tài khoản"),
    MANAGE_PERMISSIONS("MANAGE_PERMISSIONS", "Quản lý phân quyền"),
    MANAGE_SYSTEM_CONFIG("MANAGE_SYSTEM_CONFIG", "Cấu hình hệ thống"),

    MANAGE_RECRUITMENT_REQUESTS("MANAGE_RECRUITMENT_REQUESTS", "Quản lý yêu cầu tuyển dụng"),
    PROCESS_RECRUITMENT("PROCESS_RECRUITMENT", "Xử lý quy trình tuyển dụng"),
    MANAGE_CANDIDATES("MANAGE_CANDIDATES", "Quản lý ứng viên"),

    // Interview permissions (tất cả role trừ Admin đều có quyền này)
    CONDUCT_INTERVIEW("CONDUCT_INTERVIEW", "Thực hiện phỏng vấn"),
    EVALUATE_CANDIDATES("EVALUATE_CANDIDATES", "Đánh giá ứng viên"),

    SUBMIT_RECRUITMENT_REQUEST("SUBMIT_RECRUITMENT_REQUEST", "Gửi yêu cầu tuyển dụng"),
    SELECT_CANDIDATES("SELECT_CANDIDATES", "Chọn ứng viên"),

    MANAGE_RECRUITMENT_TEAM("MANAGE_RECRUITMENT_TEAM", "Quản lý đội tuyển dụng"),
    MANAGE_RECRUITMENT_POLICIES("MANAGE_RECRUITMENT_POLICIES", "Quản lý chính sách tuyển dụng"),

    HANDLE_RECRUITMENT_REQUESTS("HANDLE_RECRUITMENT_REQUESTS", "Xử lý yêu cầu tuyển dụng");

    private final String code;
    private final String description;

    PermissionType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PermissionType fromCode(String code) {
        for (PermissionType permission : values()) {
            if (permission.code.equals(code)) {
                return permission;
            }
        }
        throw new IllegalArgumentException("Invalid permission code: " + code);
    }
}
