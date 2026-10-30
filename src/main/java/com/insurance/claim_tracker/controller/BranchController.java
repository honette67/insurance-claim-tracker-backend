package com.insurance.claim_tracker.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.claim_tracker.model.Branch;
import com.insurance.claim_tracker.service.BranchService;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService){
        this.branchService = branchService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Branch branch){
        String message = branchService.save(branch);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<Branch> getAll(){
        return branchService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id){
        Optional<Branch> branch = branchService.getById(id);
        if(branch.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(branch.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The branch with that id does not exist");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Branch branch){
        String message = branchService.update(id, branch);
        if (message.equals("The branch with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        String message = branchService.delete(id);
        if (message.equals("The branch with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    //C2
    @GetMapping("/free")
    public List<Branch> getFree(){
        return branchService.getFree();
    }

}
