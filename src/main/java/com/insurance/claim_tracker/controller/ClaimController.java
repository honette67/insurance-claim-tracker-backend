package com.insurance.claim_tracker.controller;

import java.time.LocalDate;
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

import com.insurance.claim_tracker.model.Claim;
import com.insurance.claim_tracker.model.ClaimStatus;
import com.insurance.claim_tracker.service.ClaimService;

@RestController 
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService){
        this.claimService = claimService;    
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Claim claim){
        String message = claimService.save(claim);
        if(message.equals("Claim date cannot be in the past")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else if (message.equals("Adjuster already has a claim to assess on that date")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        } else{
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }
    }

    @GetMapping("/all")
    public List<Claim> getAll(){
        return claimService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id){
        Optional<Claim> claim = claimService.getById(id);
        if(claim.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(claim.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The claim with that id does not exist");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Claim claim){
        String message = claimService.update(id, claim);
        if (message.equals("The claim with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        String message = claimService.delete(id);
        if(message.equals("The claim with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.equals("That claim has payouts, delete them first")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    //B2
    @GetMapping("/by-status")
    public List<Claim> getByStatus(@RequestParam ClaimStatus status){
        return claimService.getByStatus(status);
    }

    //B3
    @GetMapping("/between")
    public List<Claim> getBetween(@RequestParam String start, @RequestParam String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return claimService.getBetween(startDate, endDate);
    }

    //D2
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable UUID id, @RequestParam ClaimStatus status){
        String message = claimService.updateStatus(id, status);
        if(message.equals("The claim with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.startsWith("Cannot change status")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    //F1
    @GetMapping("/stats/by-status")
    public List<Object[]> getStatsByStatus(){
        return claimService.getStatsByStatus();
    }

    //F2
    @PatchMapping("/cancel-day")
    public ResponseEntity<String> cancelDay(@RequestParam UUID adjusterId, @RequestParam String date){
        LocalDate day = LocalDate.parse(date);
        String message = claimService.cancelDay(adjusterId, day);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }


}
