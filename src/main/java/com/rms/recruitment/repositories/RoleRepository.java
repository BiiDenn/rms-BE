package com.rms.recruitment.repositories;

import com.rms.recruitment.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByAccountId(Integer accountId);

    List<Roles> findByRoleType(com.rms.recruitment.enums.RoleType roleType);
}
