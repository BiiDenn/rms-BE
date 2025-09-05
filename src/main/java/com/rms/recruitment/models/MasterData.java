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

    @OneToMany(mappedBy = "candProcessType")
    private List<CandidateProcess> candidateProcesses;

    @OneToMany(mappedBy = "fileTypeMasterData")
    private List<Attachments> attachmentsByFileType;

    @OneToMany(mappedBy = "fileFormatMasterData")
    private List<Attachments> attachmentsByFileFormat;
}