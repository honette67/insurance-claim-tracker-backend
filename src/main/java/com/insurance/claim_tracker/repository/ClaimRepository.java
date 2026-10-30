package com.insurance.claim_tracker.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.claim_tracker.model.Claim;
import com.insurance.claim_tracker.model.ClaimStatus;

public interface ClaimRepository extends JpaRepository<Claim, UUID>{

    //B2
    List<Claim> findByStatusOrderByClaimDateAsc(ClaimStatus status);


    //B3
    List<Claim> findByClaimDateBetweenOrderByClaimDateAsc(LocalDate start, LocalDate end);
    

}
