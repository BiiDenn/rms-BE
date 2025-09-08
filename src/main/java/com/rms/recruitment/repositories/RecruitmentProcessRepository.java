package com.rms.recruitment.repositories;

import com.rms.recruitment.models.RecruitmentProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentProcessRepository extends JpaRepository<RecruitmentProcess, Integer> {
    List<RecruitmentProcess> findByRecruitId(Integer recruitId);
}
