package com.insurance.claim_tracker.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.claim_tracker.model.Adjuster;
import com.insurance.claim_tracker.service.AdjusterService;

@RestController 
@RequestMapping("/api/adjusters")
public class AdjusterController {

    private final AdjusterService adjusterService;

    public AdjusterController(AdjusterService adjusterService){
        this.adjusterService = adjusterService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Adjuster adjuster){
        String message = adjusterService.save(adjuster);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<Adjuster> getAll(){
        return adjusterService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id){
        Optional<Adjuster> adjuster = adjusterService.getById(id);
        if (adjuster.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(adjuster.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The adjuster with that id does not exist");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Adjuster adjuster){
        String message = adjusterService.update(id,adjuster);
        if(message.equals("The adjuster with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        String message = adjusterService.delete(id);
        if(message.equals("The adjuster with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    //C1
    @GetMapping("/by-claim-type")
    public List<Adjuster> getByClaimType(@RequestParam String name){
        return adjusterService.getByClaimType(name);
    }

    //D3
    @PutMapping("/{adjusterId}/claimtypes/{claimTypeId}")
    public ResponseEntity<String> addClaimType(@PathVariable UUID adjusterId,
                                               @PathVariable UUID claimTypeId){
        String message = adjusterService.addClaimType(adjusterId, claimTypeId);
        if(message.equals("The adjuster with that id does not exist")
                || message.equals("That claim type does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.equals("The adjuster already has that claim type")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }

    }

    //D4
    @PatchMapping("/{adjusterId}/branch")
    public ResponseEntity<String> changeBranch(@PathVariable UUID adjusterId,
                                               @RequestParam int branchNumber){
        String message = adjusterService.changeBranch(adjusterId, branchNumber);
        if(message.equals("The adjuster with that id does not exist")
                || message.equals("The branch with taht branchNumber does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.equals("That branch is taken by another adjuster")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }
    
    


}
