package com.insurance.claim_tracker.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "claim")
public class Claim {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate claimDate;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;

    private String incidentDescription;
    private int claimedAmount;

    @ManyToOne 
    @JoinColumn(name = "policyholder_id")
    private Policyholder policyholder;

    @ManyToOne 
    @JoinColumn(name = "adjuster_id")
    private Adjuster adjuster;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public String getIncidentDescription() {
        return incidentDescription;
    }

    public void setIncidentDescription(String incidentDescription) {
        this.incidentDescription = incidentDescription;
    }

    public int getClaimedAmount() {
        return claimedAmount;
    }

    public void setClaimedAmount(int claimedAmount) {
        this.claimedAmount = claimedAmount;
    }

    public Policyholder getPolicyholder() {
        return policyholder;
    }

    public void setPolicyholder(Policyholder policyholder) {
        this.policyholder = policyholder;
    }

    public Adjuster getAdjuster() {
        return adjuster;
    }

    public void setAdjuster(Adjuster adjuster) {
        this.adjuster = adjuster;
    }

}