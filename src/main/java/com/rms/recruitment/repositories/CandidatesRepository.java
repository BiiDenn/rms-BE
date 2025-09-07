package com.rms.recruitment.repositories;

import com.rms.recruitment.models.Candidates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatesRepository extends JpaRepository<Candidates, Integer> {
    List<Candidates> findByJobTitleId(Integer jobTitleId);

    List<Candidates> findByEmployeeId(Integer employeeId);
}
