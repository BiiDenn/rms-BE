package com.rms.recruitment.repositories;

import com.rms.recruitment.models.RecruitmentProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitmentProcessRepository extends JpaRepository<RecruitmentProcess, Integer> {
    List<RecruitmentProcess> findByRecruitId(Integer recruitId);
    
    @Query("SELECT DISTINCT rp FROM RecruitmentProcess rp " +
           "LEFT JOIN FETCH rp.recruitment r " +
           "LEFT JOIN FETCH r.division d " +
           "WHERE rp.recruitProcessId IN (" +
           "    SELECT cp.recruitProcessId FROM CandidateProcess cp " +
           "    WHERE cp.candId = :candidateId" +
           ")")
    List<RecruitmentProcess> findByCandidateId(@Param("candidateId") Integer candidateId);
    
    @Query("SELECT rp FROM RecruitmentProcess rp " +
           "LEFT JOIN FETCH rp.recruitment r " +
           "LEFT JOIN FETCH r.division d " +
           "WHERE rp.recruitProcessId = :processId")
    Optional<RecruitmentProcess> findByIdWithRelations(@Param("processId") Integer processId);
    
    @Query("SELECT rp FROM RecruitmentProcess rp " +
           "LEFT JOIN FETCH rp.recruitment r " +
           "LEFT JOIN FETCH r.division d " +
           "WHERE rp.recruitId = :recruitId")
    List<RecruitmentProcess> findByRecruitIdWithRelations(@Param("recruitId") Integer recruitId);

    @Query("SELECT rp FROM RecruitmentProcess rp " +
           "LEFT JOIN FETCH rp.recruitment r " +
           "LEFT JOIN FETCH r.division d " +
           "WHERE rp.recruitProcessId = :recruitProcessId")
    Optional<RecruitmentProcess> fetchRecruitmentInfoByProcessId(@Param("recruitProcessId") Integer recruitProcessId);
}
