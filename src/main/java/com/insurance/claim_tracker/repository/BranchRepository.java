package com.insurance.claim_tracker.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.insurance.claim_tracker.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, UUID> {

    //C2
    @Query("SELECT b FROM Branch b WHERE NOT EXISTS (SELECT a FROM Adjuster a WHERE a.branch = b) ORDER BY b.branchNumber")
    List<Branch> getFreeBranches();

    //D4
    Optional<Branch> findByBranchNumber(int branchNumber);

}
