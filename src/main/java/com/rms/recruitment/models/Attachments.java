package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attachments")
public class Attachments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AttachmentID")
    private Integer attachmentId;

    @Column(name = "FileName", length = 255)
    private String fileName;

    @Column(name = "FileType")
    private Integer fileType;

    @Column(name = "FileFormat")
    private Integer fileFormat;

    @Column(name = "Description", length = 500)
    private String description;

    @Column(name = "Url", length = 500)
    private String url;

    @Column(name = "UploadDate")
    private LocalDate uploadDate;

    // Relations to MasterData to satisfy mappedBy in MasterData
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FileType", referencedColumnName = "MasterDataID", insertable = false, updatable = false)
    private MasterData fileTypeMasterData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FileFormat", referencedColumnName = "MasterDataID", insertable = false, updatable = false)
    private MasterData fileFormatMasterData;

}