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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.claim_tracker.model.Policyholder;
import com.insurance.claim_tracker.service.PolicyholderService;

@RestController 
@RequestMapping("/api/policyholders")
public class PolicyholderController {

    private final PolicyholderService policyholderService;

    public PolicyholderController(PolicyholderService policyholderService){
        this.policyholderService = policyholderService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Policyholder policyholder){
        String message = policyholderService.save(policyholder);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<Policyholder> getAll(){
        return policyholderService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable  UUID id ){
        Optional<Policyholder> policyholder = policyholderService.getById(id);
        if(policyholder.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(policyholder.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The policyholder with that id does not exist");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Policyholder policyholder){
       String message = policyholderService.update(id, policyholder); 
       if(message.equals("The policyholder with that id does not exist")){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
       } else {
        return ResponseEntity.status(HttpStatus.OK).body(message);
       }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        String message = policyholderService.delete(id);
        if(message.equals("The policyholder with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    //B1
    @GetMapping("/by-last-name")
    public List<Policyholder> getByLastName(@RequestParam String lastName){
        return policyholderService.getByLastName(lastName);
    }

    //C4
    @GetMapping("/of-adjuster/{adjusterId}")
    public ResponseEntity<Object> getOfAdjuster(@PathVariable UUID adjusterId){
        Optional<List<Policyholder>> result = policyholderService.getOfAdjuster(adjusterId);
        if (result.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The adjuster with that id does not exist");
        }
    }

}
