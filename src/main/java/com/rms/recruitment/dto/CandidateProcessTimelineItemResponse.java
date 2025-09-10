package com.rms.recruitment.dto;

import java.time.LocalDate;

public class CandidateProcessTimelineItemResponse {
    private Integer id;
    private LocalDate processDate;
    private Integer typeId;
    private String typeName;
    private String status;
    private String description;
    private Integer interviewerId;
    private String interviewerName;
    private String interviewerEmail;
    private Integer locationId;
    private String locationName;
    private String url;
    private String note;

    // Default constructor
    public CandidateProcessTimelineItemResponse() {
    }

    // Constructor with all fields
    public CandidateProcessTimelineItemResponse(Integer id, LocalDate processDate, Integer typeId, String typeName,
            String status, String description, Integer interviewerId, String interviewerName,
            String interviewerEmail, Integer locationId, String locationName,
            String url, String note) {
        this.id = id;
        this.processDate = processDate;
        this.typeId = typeId;
        this.typeName = typeName;
        this.status = status;
        this.description = description;
        this.interviewerId = interviewerId;
        this.interviewerName = interviewerName;
        this.interviewerEmail = interviewerEmail;
        this.locationId = locationId;
        this.locationName = locationName;
        this.url = url;
        this.note = note;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getProcessDate() {
        return processDate;
    }

    public void setProcessDate(LocalDate processDate) {
        this.processDate = processDate;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(Integer interviewerId) {
        this.interviewerId = interviewerId;
    }

    public String getInterviewerName() {
        return interviewerName;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }

    public String getInterviewerEmail() {
        return interviewerEmail;
    }

    public void setInterviewerEmail(String interviewerEmail) {
        this.interviewerEmail = interviewerEmail;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // Static method to create builder
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private Integer id;
        private LocalDate processDate;
        private Integer typeId;
        private String typeName;
        private String status;
        private String description;
        private Integer interviewerId;
        private String interviewerName;
        private String interviewerEmail;
        private Integer locationId;
        private String locationName;
        private String url;
        private String note;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder processDate(LocalDate processDate) {
            this.processDate = processDate;
            return this;
        }

        public Builder typeId(Integer typeId) {
            this.typeId = typeId;
            return this;
        }

        public Builder typeName(String typeName) {
            this.typeName = typeName;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder interviewerId(Integer interviewerId) {
            this.interviewerId = interviewerId;
            return this;
        }

        public Builder interviewerName(String interviewerName) {
            this.interviewerName = interviewerName;
            return this;
        }

        public Builder interviewerEmail(String interviewerEmail) {
            this.interviewerEmail = interviewerEmail;
            return this;
        }

        public Builder locationId(Integer locationId) {
            this.locationId = locationId;
            return this;
        }

        public Builder locationName(String locationName) {
            this.locationName = locationName;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder note(String note) {
            this.note = note;
            return this;
        }

        public CandidateProcessTimelineItemResponse build() {
            return new CandidateProcessTimelineItemResponse(id, processDate, typeId, typeName, status, description,
                    interviewerId, interviewerName, interviewerEmail, locationId,
                    locationName, url, note);
        }
    }
}
