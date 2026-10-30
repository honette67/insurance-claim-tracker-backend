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

import com.insurance.claim_tracker.model.ClaimType;
import com.insurance.claim_tracker.service.ClaimTypeService;

@RestController 
@RequestMapping("/api/claimtypes")
public class ClaimTypeController {

    private final ClaimTypeService claimTypeService;

    public ClaimTypeController(ClaimTypeService claimTypeService){
        this.claimTypeService = claimTypeService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ClaimType claimType){
        String message = claimTypeService.save(claimType);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<ClaimType> getAll(){
        return claimTypeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id){
        Optional<ClaimType> claimType = claimTypeService.getById(id);
        if(claimType.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(claimType.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The claim type with that id does not exist");
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody ClaimType claimType){
        String message = claimTypeService.update(id, claimType);
        if (message.equals("The claim type with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        String message = claimTypeService.delete(id);
        if(message.equals("The claim type with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    //C3
    @GetMapping("/unused")
    public List<ClaimType> getUnused(){
        return claimTypeService.getUnused();
    }
}
