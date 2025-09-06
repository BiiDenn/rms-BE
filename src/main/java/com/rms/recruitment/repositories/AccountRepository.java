package com.rms.recruitment.repositories;

import com.rms.recruitment.models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer> {
    Optional<Accounts> findByEmail(String email);

    boolean existsByEmail(String email);
}
