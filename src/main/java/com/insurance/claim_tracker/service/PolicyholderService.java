package com.insurance.claim_tracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.insurance.claim_tracker.model.Policyholder;
import com.insurance.claim_tracker.repository.AdjusterRepository;
import com.insurance.claim_tracker.repository.PolicyholderRepository;

@Service 
public class PolicyholderService {

    private final PolicyholderRepository policyholderRepository;
    private final AdjusterRepository adjusterRepository;

    public PolicyholderService(PolicyholderRepository policyholderRepository,
        AdjusterRepository adjusterRepository){
        
        this.adjusterRepository = adjusterRepository;
        this.policyholderRepository = policyholderRepository;
    }

    public String save(Policyholder policyholder){
        policyholderRepository.save(policyholder);
        return "Policyholder saved successfully";
    }

    public List<Policyholder> getAll(){
        return policyholderRepository.findAll();
    }

    public Optional<Policyholder> getById(UUID id){
        return policyholderRepository.findById(id);
    }

    public String update(UUID id, Policyholder newData){
        Optional<Policyholder> found = policyholderRepository.findById(id);
        if(found.isEmpty()){
            return "The policyholder with that id does not exist";
        }
        Policyholder p = found.get();
        p.setFirstName(newData.getFirstName());
        p.setLastName(newData.getLastName());
        p.setPolicyNumber(newData.getPolicyNumber());
        p.setPhone(newData.getPhone());
        p.setEmail(newData.getEmail());
        policyholderRepository.save(p);
        return "Policyholder updated successfully";
    }

    public String delete(UUID id){
        if(!policyholderRepository.existsById(id)){
            return "The policyholder with that id does not exist";
        }
        policyholderRepository.deleteById(id);
        return "Policyholder deleted successfully";
    }

    //B1
    public List<Policyholder> getByLastName(String lastName){
        return policyholderRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    //C4
    public Optional<List<Policyholder>> getOfAdjuster(UUID adjusterId){
        if (!adjusterRepository.existsById(adjusterId)){
            return Optional.empty();
        }
        return Optional.of(policyholderRepository.getPolicyholdersOfAdjuster(adjusterId));
    }

}
