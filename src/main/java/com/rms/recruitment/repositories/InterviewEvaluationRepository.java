package com.rms.recruitment.repositories;

import com.rms.recruitment.models.InterviewEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewEvaluationRepository extends JpaRepository<InterviewEvaluation, Integer> {
    List<InterviewEvaluation> findByEmployeeId(Integer employeeId);
}
