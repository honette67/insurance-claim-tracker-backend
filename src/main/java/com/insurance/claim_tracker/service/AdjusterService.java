package com.insurance.claim_tracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.insurance.claim_tracker.model.Adjuster;
import com.insurance.claim_tracker.model.Branch;
import com.insurance.claim_tracker.model.ClaimType;
import com.insurance.claim_tracker.repository.AdjusterRepository;
import com.insurance.claim_tracker.repository.BranchRepository;
import com.insurance.claim_tracker.repository.ClaimTypeRepository;

import jakarta.transaction.Transactional;

@Service 
public class AdjusterService {

    private final AdjusterRepository adjusterRepository;
    private final ClaimTypeRepository claimTypeRepository;
    private final BranchRepository branchRepository;

    public AdjusterService(AdjusterRepository adjusterRepository,
                           ClaimTypeRepository claimTypeRepository,
                           BranchRepository branchRepository){
       
        this.adjusterRepository = adjusterRepository;
        this.claimTypeRepository = claimTypeRepository;
        this.branchRepository = branchRepository;
    }

    public String save(Adjuster adjuster){
        adjusterRepository.save(adjuster);
        return "Adjuster saved successfully";
    }

    public List<Adjuster> getAll(){
        return adjusterRepository.findAll();
    }

    public Optional<Adjuster> getById(UUID id){
        return adjusterRepository.findById(id);
    }

    public String update(UUID id, Adjuster newData){
        Optional<Adjuster> found = adjusterRepository.findById(id);
        if(found.isEmpty()){
            return "The adjuster with that id does not exist";
        }
        Adjuster adjuster = found.get();
        adjuster.setFirstName(newData.getFirstName());
        adjuster.setLastName(newData.getLastName());
        adjuster.setLicenceNumber(newData.getLicenceNumber());
        adjuster.setBranch(newData.getBranch());
        adjuster.setClaimTypes(newData.getClaimTypes());
        adjusterRepository.save(adjuster);
        return "Adjuster updated successfully";
    }

    public String delete(UUID id){
        if(!adjusterRepository.existsById(id)){
            return "The adjuster with that id does not exist";
        }
        adjusterRepository.deleteById(id);
        return "Adjuster deleted successfully";
    }

    //C1
    public List<Adjuster> getByClaimType(String name){
        return adjusterRepository.getAdjustersByClaimTypeName(name);
    }

    //D3
    @Transactional
    public String addClaimType(UUID adjusterId, UUID claimTypeId){
        Optional<Adjuster> adjuster = adjusterRepository.findById(adjusterId);
        if (adjuster.isEmpty()){
            return "The adjuster with that id does not exist";
        }
        Optional<ClaimType> claimType = claimTypeRepository.findById(claimTypeId);
        if (claimType.isEmpty()){
            return "That claim type does not exist";
        }
        if (adjuster.get().getClaimTypes().contains(claimType.get())){
            return "The adjuster already has that claim type";
        }
        adjuster.get().getClaimTypes().add(claimType.get());
        adjusterRepository.save(adjuster.get());
        return "Claim type added to the adjuster";
    }

    //D4
    public String changeBranch(UUID adjusterId, int branchNumber){
        Optional<Adjuster> adjuster = adjusterRepository.findById(adjusterId);
        if (adjuster.isEmpty()){
            return "The adjuster with that id does not exist";
        }
        Optional<Branch> branch = branchRepository.findByBranchNumber(branchNumber);
        if (branch.isEmpty()){
           return "The branch with that branchNumber does not exist"; 
        }
        if (adjusterRepository.existsByBranch(branch.get())){
            return "That branch is taken by another adjuster";
        }
        adjuster.get().setBranch(branch.get());
        adjusterRepository.save(adjuster.get());
        return "Branch changed successfully";
    }

}
