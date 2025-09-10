package com.rms.recruitment.repositories;

import com.rms.recruitment.models.CandidateProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateProcessRepository extends JpaRepository<CandidateProcess, Integer> {
    List<CandidateProcess> findByCandId(Integer candId);
    List<CandidateProcess> findByRecruitProcessId(Integer recruitProcessId);

    @Query("SELECT cp FROM CandidateProcess cp " +
           "LEFT JOIN FETCH cp.location l " +
           "LEFT JOIN FETCH cp.interviewer iv " +
           "WHERE cp.candId = :candidateId AND cp.recruitProcessId = :recruitProcessId " +
           "ORDER BY cp.processDate ASC, cp.candProcessId ASC")
    List<CandidateProcess> findTimelineByCandidateAndRecruitProcess(
            @Param("candidateId") Integer candidateId,
            @Param("recruitProcessId") Integer recruitProcessId);
}
