package com.rms.recruitment.repositories;

import com.rms.recruitment.models.CandidateProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
            
    // Lấy tất cả process của một candidate, sắp xếp theo ngày mới nhất
    List<CandidateProcess> findByCandIdOrderByProcessDateDesc(Integer candId);

    // Lấy process mới nhất của một candidate
    @Query("SELECT cp FROM CandidateProcess cp WHERE cp.candId = :candId ORDER BY cp.processDate DESC LIMIT 1")
    Optional<CandidateProcess> findLatestByCandId(@Param("candId") Integer candId);

    // Lấy process theo tên process
    List<CandidateProcess> findByCandIdAndCandProcessNameOrderByProcessDateDesc(Integer candId, String processName);
}
