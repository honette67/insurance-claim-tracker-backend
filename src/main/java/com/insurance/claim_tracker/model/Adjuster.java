package com.insurance.claim_tracker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "adjuster")
public class Adjuster {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String licenceNumber;

    @ManyToOne 
    @JoinColumn(name = "branch_id", nullable = true)
    private Branch branch;

    @ManyToMany
    @JoinTable(
        name = "adjuster_claim_type",
        joinColumns = @JoinColumn(name = "adjuster_id"),
        inverseJoinColumns = @JoinColumn(name = "claim_type_id")
    )
    private List<ClaimType> claimTypes = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<ClaimType> getClaimTypes() {
        return claimTypes;
    }

    public void setClaimTypes(List<ClaimType> claimTypes) {
        this.claimTypes = claimTypes;
    }



    
}
