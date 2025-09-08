package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phone_itv_experience")
public class PhoneItvExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experienceId")
    private Integer experienceId;

    @Column(name = "phoneEvalId")
    private Integer phoneEvalId;

    @ManyToOne
    @JoinColumn(name = "phoneEvalId", referencedColumnName = "phoneEvalId", insertable = false, updatable = false)
    private PhoneItvEval phoneItvEval;

    @Column(name = "experienceYear")
    private Integer experienceYear;

    @Column(name = "domain", length = 255)
    private String domain;

    @Column(name = "technologyUsed", length = 255)
    private String technologyUsed;

    @Column(name = "supportTools", length = 255)
    private String supportTools;

    @Lob
    @Column(name = "workExperience")
    private String workExperience;
}
