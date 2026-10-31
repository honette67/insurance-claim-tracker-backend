package com.insurance.claim_tracker.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.insurance.claim_tracker.model.Claim;
import com.insurance.claim_tracker.model.ClaimStatus;
import com.insurance.claim_tracker.model.Payout;
import com.insurance.claim_tracker.repository.ClaimRepository;
import com.insurance.claim_tracker.repository.PayoutRepository;

@Service 
public class PayoutService {

    private final PayoutRepository payoutRepository;
    private final ClaimRepository claimRepository;

    public PayoutService(PayoutRepository payoutRepository, ClaimRepository claimRepository){
        this.payoutRepository = payoutRepository;
        this.claimRepository = claimRepository;
    }

    //E1
    public String save(UUID claimId, Payout payout){
        Optional<Claim> claim = claimRepository.findById(claimId);
        if(claim.isEmpty()){
            return "The claim with that id does not exist";
        }
        if(claim.get().getStatus() != ClaimStatus.SETTLED){
            return "Payouts are only for SETTLED claims";
        }
        payout.setPaidDate(LocalDate.now());
        payout.setClaim(claim.get());
        payoutRepository.save(payout);
        return "Payout saved successfully";
    }

    //E2
    public List<Payout> getByPolicyholder(String policyNumber){
        return payoutRepository.getPayoutsOfPolicyholder(policyNumber);
    }

    //E3
    public String delete(UUID id){
        if(!payoutRepository.existsById(id)){
            return "The payout with that id does not exixst";
        }
        payoutRepository.deleteById(id);
        return "Payout deleted successfully";
    }

}
