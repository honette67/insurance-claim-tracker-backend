package com.insurance.claim_tracker.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.insurance.claim_tracker.model.ClaimStatus;
import com.insurance.claim_tracker.repository.AdjusterRepository;
import com.insurance.claim_tracker.repository.BranchRepository;
import com.insurance.claim_tracker.repository.ClaimRepository;
import com.insurance.claim_tracker.repository.PayoutRepository;
import com.insurance.claim_tracker.repository.PolicyholderRepository;

@Service 
public class DashboardService {
    
    private final PolicyholderRepository policyholderRepository;
    private final AdjusterRepository adjusterRepository;
    private final BranchRepository branchRepository;
    private final ClaimRepository claimRepository;
    private final PayoutRepository payoutRepository;

    public DashboardService(PolicyholderRepository policyholderRepository,
                            AdjusterRepository adjusterRepository,
                            BranchRepository branchRepository,
                            ClaimRepository claimRepository,
                            PayoutRepository payoutRepository){
        this.policyholderRepository = policyholderRepository;
        this.adjusterRepository = adjusterRepository;
        this.branchRepository = branchRepository;
        this.claimRepository = claimRepository;
        this.payoutRepository = payoutRepository;

    }

    public Map<String, Long> getSummary(){
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("policyholders", policyholderRepository.count());
        summary.put("adjusters", adjusterRepository.count());
        summary.put("freeBranches", (long) branchRepository.getFreeBranches().size());
        summary.put("claimsToday", claimRepository.countByClaimDate(LocalDate.now()));
        summary.put("filed", claimRepository.countByStatus(ClaimStatus.FILED));
        summary.put("payouts",payoutRepository.count());
        return summary;
        
    }
    

}
