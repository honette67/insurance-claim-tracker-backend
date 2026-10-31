package com.insurance.claim_tracker.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insurance.claim_tracker.model.Claim;
import com.insurance.claim_tracker.model.ClaimStatus;

public interface ClaimRepository extends JpaRepository<Claim, UUID>{

    //B2
    List<Claim> findByStatusOrderByClaimDateAsc(ClaimStatus status);


    //B3
    List<Claim> findByClaimDateBetweenOrderByClaimDateAsc(LocalDate start, LocalDate end);

    //D1
    boolean existsByAdjusterIdAndClaimDateAndStatusNot(UUID adjusterId, LocalDate claimDate, ClaimStatus status);

    //F1
    @Query("SELECT c.status, COUNT(c) FROM Claim c GROUP BY c.status")
    List<Object[]> countClaimsByStatus();

    //F2
    @Modifying 
    @Query("UPDATE Claim c SET c.status = :rejected WHERE c.adjuster.id = :adjusterId AND c.claimDate = :date And c.status <> :settled")
    int cancelDay(@Param("adjusterId") UUID adjusterId,
                  @Param("date") LocalDate date,
                  @Param("rejected") ClaimStatus rejected,
                  @Param("settled") ClaimStatus settled);

    //Bonus
    long countByClaimDate(LocalDate date);
    long countByStatus(ClaimStatus status);
    

}
