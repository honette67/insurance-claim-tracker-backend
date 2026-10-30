package com.insurance.claim_tracker.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.insurance.claim_tracker.model.ClaimType;

public interface ClaimTypeRepository extends JpaRepository<ClaimType, UUID>{

    //C3
    @Query("SELECT c FROM ClaimType c WHERE c.adjusters IS EMPTY")
    List<ClaimType> getUnusedClaimTypes();

}
