package com.insurance.claim_tracker.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.insurance.claim_tracker.model.Claim;
import com.insurance.claim_tracker.model.ClaimStatus;
import com.insurance.claim_tracker.repository.ClaimRepository;

@Service 
public class ClaimService {

    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository){
        this.claimRepository = claimRepository;
    }

    public String save(Claim claim){
        claimRepository.save(claim);
        return "Claim saved successfully";
    }

    public List<Claim> getAll(){
        return claimRepository.findAll();
    }

    public Optional<Claim> getById(UUID id){
        return claimRepository.findById(id);
    }

    public String update(UUID id, Claim newData){
        Optional<Claim> found = claimRepository.findById(id);

        if(found.isEmpty()){
            return "The claim with that id does not exist";
        }

        Claim claim = found.get();
        claim.setClaimDate(newData.getClaimDate());
        claim.setIncidentDescription(newData.getIncidentDescription());
        claim.setClaimedAmount(newData.getClaimedAmount());
        claim.setPolicyholder(newData.getPolicyholder());
        claim.setAdjuster(newData.getAdjuster());

        claimRepository.save(claim);
        return "Claim updated successfully";
    }

    public String delete(UUID id){
        if(!claimRepository.existsById(id)){
            return "The Claim with that id does not exist";

        }
        claimRepository.deleteById(id);
        return "Claim deleted successfully";
    }

    //B2
    public List<Claim> getByStatus(ClaimStatus status){
        return claimRepository.findByStatusOrderByClaimDateAsc(status);
    }

    //B3
    public List<Claim> getBetween(LocalDate start, LocalDate end){
        return claimRepository.findByClaimDateBetweenOrderByClaimDateAsc(start, end);
    }
}
