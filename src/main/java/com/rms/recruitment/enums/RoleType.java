package com.rms.recruitment.enums;

public enum RoleType {
    ADMIN("Admin", "Quản trị viên hệ thống"),
    RECRUITER("Recruiter", "Nhân viên đội tuyển dụng"),
    HRD("HRD", "Quản lý đội tuyển dụng"),
    REQUESTOR("Requestor", "Trưởng phòng hoặc phó phòng ban"),
    INTERVIEWER("Interviewer", "Người thực hiện phỏng vấn");

    private final String code;
    private final String description;

    RoleType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static RoleType fromCode(String code) {
        for (RoleType role : values()) {
            if (role.code.equals(code)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role code: " + code);
    }
}
