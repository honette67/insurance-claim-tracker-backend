package com.insurance.claim_tracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.insurance.claim_tracker.model.ClaimType;
import com.insurance.claim_tracker.repository.ClaimTypeRepository;

@Service 
public class ClaimTypeService {

    private final ClaimTypeRepository claimTypeRepository;

    public ClaimTypeService(ClaimTypeRepository claimTypeRepository){
        this.claimTypeRepository = claimTypeRepository;
    }

    public String save(ClaimType claimType){
        claimTypeRepository.save(claimType);
        return "Claim type saved successfully";
    }

    public List<ClaimType> getAll(){
        return claimTypeRepository.findAll();
    }

    public Optional<ClaimType> getById(UUID id){
        return claimTypeRepository.findById(id);
    }

    public String update(UUID id, ClaimType newData){
        Optional<ClaimType> found = claimTypeRepository.findById(id);
        if(found.isEmpty()){
            return "The claim type with that id does not exist";
        }
        ClaimType claimType = found.get();
        claimType.setName(newData.getName());
        claimTypeRepository.save(claimType);
        return "Claim type updated successfully";

    }

    public String delete(UUID id){
        if(!claimTypeRepository.existsById(id)){
            return "The claim type with that id does not exist";
        }
        claimTypeRepository.deleteById(id);
        return "Claim type deleted successfully";
    }

    //C3
    public List<ClaimType> getUnused(){
        return claimTypeRepository.getUnusedClaimTypes();
    }
}
