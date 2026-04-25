package com.internship.tool.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendors")
public class Vendor {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Vendor company name
    @Column(name = "vendor_name", nullable = false)
    private String vendorName;

    // Contact person
    @Column(name = "contact_person")
    private String contactPerson;

    // Email
    @Column(unique = true, nullable = false)
    private String email;

    // Phone number
    private String phone;

    // Risk score
    @Column(name = "risk_score")
    private Integer riskScore;

    // Status
    private String status;

    // Description
    @Column(columnDefinition = "TEXT")
    private String description;

    // Review date
    @Column(name = "review_date")
    private LocalDate reviewDate;

    // Soft delete
    private Boolean deleted = false;

    // Created timestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Updated timestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ==========================================
    // Getters and Setters
    // ==========================================

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getVendorName() { return vendorName; }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getContactPerson() { return contactPerson; }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public Integer getRiskScore() { return riskScore; }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReviewDate() { return reviewDate; }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Boolean getDeleted() { return deleted; }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}