package com.insurance.claim_tracker.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insurance.claim_tracker.model.Claim;
import com.insurance.claim_tracker.model.Payout;

public interface PayoutRepository extends JpaRepository<Payout, UUID>{

    //E2
    @Query("SELECT d FROM Payout d WHERE d.claim.policyholder.policyNumber = :policyNumber ORDER BY d.paidDate DESC")
    List<Payout> getPayoutsOfPolicyholder(@Param("policyNumber") String policyNumber);

    //E3
    boolean existsByClaim(Claim claim);

}
