package com.insurance.claim_tracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.insurance.claim_tracker.model.Adjuster;
import com.insurance.claim_tracker.repository.AdjusterRepository;

@Service 
public class AdjusterService {

    private final AdjusterRepository adjusterRepository;

    public AdjusterService(AdjusterRepository adjusterRepository){
        this.adjusterRepository = adjusterRepository;
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

}
