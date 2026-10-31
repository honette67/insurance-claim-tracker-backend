package com.insurance.claim_tracker.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "payout")
public class Payout {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String payoutItem;
    private String remarks;
    private int amountPaid;
    private LocalDate paidDate;

    @ManyToOne 
    @JoinColumn(name = "claim_id")
    private Claim claim;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPayoutItem() {
        return payoutItem;
    }

    public void setPayoutItem(String payoutItem) {
        this.payoutItem = payoutItem;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }



}
