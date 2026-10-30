package com.insurance.claim_tracker.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insurance.claim_tracker.model.Policyholder;

public interface PolicyholderRepository extends JpaRepository<Policyholder, UUID>{

    //B1
    List<Policyholder> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    //C4
    @Query("SELECT DISTINCT p FROM Claim c JOIN c.policyholder p WHERE c.adjuster.id = :adjusterId")
    List<Policyholder> getPolicyholdersOfAdjuster(@Param("adjusterId") UUID adjusterId);

}
