package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phone_itv_eval_detail")
public class PhoneItvEvalDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phoneEvalDetailId")
    private Integer phoneEvalDetailId;

    @Lob
    @Column(name = "note")
    private String note;

    @Column(name = "phoneEvalId")
    private Integer phoneEvalId;

    @ManyToOne
    @JoinColumn(name = "phoneEvalId", referencedColumnName = "phoneEvalId", insertable = false, updatable = false)
    private PhoneItvEval phoneItvEval;
}