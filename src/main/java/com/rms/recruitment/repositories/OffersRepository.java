package com.rms.recruitment.repositories;

import com.rms.recruitment.models.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffersRepository extends JpaRepository<Offers, Integer> {
}

