package com.insurance.claim_tracker.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.claim_tracker.model.Payout;
import com.insurance.claim_tracker.service.PayoutService;

@RestController 
@RequestMapping("/api/payouts")
public class PayoutController {

    private final PayoutService payoutService;

    public PayoutController(PayoutService payoutService){
        this.payoutService = payoutService;
    }

    //E1
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID claimId, @RequestBody Payout payout){
       String message = payoutService.save(claimId, payout);
       if (message.equals("The claim with that id does not exist")){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
       } else if (message.equals("Payouts are only for SETTLED claims")){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
       } else {
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
       }
    }

    //E2
    @GetMapping("/policyholder/{policyNumber}")
    public List<Payout> getByPolicyholder(@PathVariable String policyNumber){
        return payoutService.getByPolicyholder(policyNumber);
    }

    //E3
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        String message = payoutService.delete(id);
        if(message.equals("The payout with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

}
