package com.insurance.claim_tracker.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.insurance.claim_tracker.model.Branch;
import com.insurance.claim_tracker.repository.BranchRepository;

@Service 
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository){
        this.branchRepository = branchRepository;
    }

    public String save(Branch branch){
        branchRepository.save(branch);
        return "Branch saved successfully";
    }

    public List<Branch> getAll(){
        return branchRepository.findAll();
    }

    public Optional<Branch> getById(UUID id){
        return branchRepository.findById(id);
    }

    public String update(UUID id, Branch newData){
        Optional<Branch> found = branchRepository.findById(id);
        if(found.isEmpty()){
            return "The branch with that id does not exist";
        }
        Branch branch = found.get();
        branch.setBranchNumber(newData.getBranchNumber());
        branch.setName(newData.getName());
        branchRepository.save(branch);
        return "Branch updated successfully";
    }

    public String delete(UUID id){
        if(!branchRepository.existsById(id)){
            return "The branch with that id does not exist";
        }
        branchRepository.deleteById(id);
        return "Branch deleted successfully";
    }

    //C2
    public List<Branch> getFree(){
        return branchRepository.getFreeBranches();
    }

}
