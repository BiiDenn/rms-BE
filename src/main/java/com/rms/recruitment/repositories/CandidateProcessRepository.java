package com.rms.recruitment.repositories;

import com.rms.recruitment.models.CandidateProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateProcessRepository extends JpaRepository<CandidateProcess, Integer> {
    List<CandidateProcess> findByCandId(Integer candId);
    List<CandidateProcess> findByRecruitProcessId(Integer recruitProcessId);
}
