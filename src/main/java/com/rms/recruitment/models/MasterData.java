package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "masterdata")
public class MasterData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MasterDataID")
    private Integer masterDataId;

    @Column(name = "Category", nullable = false, length = 100)
    private String category;

    @Column(name = "Code", nullable = false, length = 50, unique = true)
    private String code;

    @Column(name = "Name", nullable = false, length = 200)
    private String name;

    @Column(name = "LanguageNo", length = 4)
    private String languageNo;

    @Lob
    @Column(name = "Description")
    private String description;

    @Column(name = "Status", length = 50)
    private String status;

    @OneToMany(mappedBy = "genderMasterData")
    private List<Candidates> candidatesByGender;

    @OneToMany(mappedBy = "fileTypeMasterData")
    private List<Attachments> attachmentsByFileType;

    @OneToMany(mappedBy = "fileFormatMasterData")
    private List<Attachments> attachmentsByFileFormat;

    public Integer getMasterDataId() {
        return masterDataId;
    }

    public void setMasterDataId(Integer masterDataId) {
        this.masterDataId = masterDataId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguageNo() {
        return languageNo;
    }

    public void setLanguageNo(String languageNo) {
        this.languageNo = languageNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Candidates> getCandidatesByGender() {
        return candidatesByGender;
    }

    public void setCandidatesByGender(List<Candidates> candidatesByGender) {
        this.candidatesByGender = candidatesByGender;
    }

    public List<Attachments> getAttachmentsByFileType() {
        return attachmentsByFileType;
    }

    public void setAttachmentsByFileType(List<Attachments> attachmentsByFileType) {
        this.attachmentsByFileType = attachmentsByFileType;
    }

    public List<Attachments> getAttachmentsByFileFormat() {
        return attachmentsByFileFormat;
    }

    public void setAttachmentsByFileFormat(List<Attachments> attachmentsByFileFormat) {
        this.attachmentsByFileFormat = attachmentsByFileFormat;
    }
}