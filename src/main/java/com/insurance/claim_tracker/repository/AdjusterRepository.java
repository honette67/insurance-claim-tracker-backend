package com.insurance.claim_tracker.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insurance.claim_tracker.model.Adjuster;
import com.insurance.claim_tracker.model.Branch;

public interface AdjusterRepository extends JpaRepository<Adjuster, UUID>{

    //C1
    @Query("SELECT DISTINCT a FROM Adjuster a JOIN a.claimTypes c WHERE LOWER(c.name) = LOWER(:name)")
    List<Adjuster> getAdjustersByClaimTypeName(@Param ("name") String name);

    //D4
    boolean existsByBranch(Branch branch);

}
