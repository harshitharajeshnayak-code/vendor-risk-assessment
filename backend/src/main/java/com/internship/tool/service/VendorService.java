package com.internship.tool.service;

import com.internship.tool.entity.Vendor;
import com.internship.tool.repository.VendorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    // ==========================================
    // Get all vendors with pagination
    // ==========================================
    public Page<Vendor> getAllVendors(Pageable pageable) {

        return vendorRepository.findAll(pageable);
    }

    // ==========================================
    // Get vendor by ID
    // ==========================================
    public Vendor getVendorById(Long id) {

        return vendorRepository.findById(id).orElse(null);
    }

    // ==========================================
    // Save new vendor
    // ==========================================
    public Vendor createVendor(Vendor vendor) {

        return vendorRepository.save(vendor);
    }

    // ==========================================
    // Update vendor
    // ==========================================
    public Vendor updateVendor(Long id, Vendor updatedVendor) {

        Vendor vendor = vendorRepository.findById(id).orElse(null);

        if (vendor != null) {

            vendor.setVendorName(updatedVendor.getVendorName());
            vendor.setEmail(updatedVendor.getEmail());
            vendor.setStatus(updatedVendor.getStatus());
            vendor.setRiskScore(updatedVendor.getRiskScore());

            return vendorRepository.save(vendor);
        }

        return null;
    }

    // ==========================================
    // Soft delete vendor
    // ==========================================
    public void softDeleteVendor(Long id) {

        Vendor vendor = vendorRepository.findById(id).orElse(null);

        if (vendor != null) {
            vendor.setDeleted(true);
            vendorRepository.save(vendor);
        }
    }

    // ==========================================
    // Search vendor
    // ==========================================
    public List<Vendor> searchVendor(String keyword) {

        return vendorRepository.searchVendors(
                keyword,
                Pageable.unpaged()
        ).getContent();
    }

    // ==========================================
    // Dashboard stats
    // ==========================================
    public java.util.Map<String, Object> getDashboardStats() {

        long total = vendorRepository.count();

        long active = vendorRepository.findByDeletedFalse().size();

        return java.util.Map.of(
                "totalVendors", total,
                "activeVendors", active
        );
    }

    // ==========================================
    // Export CSV
    // ==========================================
    public String exportVendorsToCsv() {

        StringBuilder csv = new StringBuilder();

        csv.append("ID,Vendor Name,Email,Status,Risk Score\n");

        vendorRepository.findAll().forEach(vendor -> {

            csv.append(vendor.getId()).append(",");
            csv.append(vendor.getVendorName()).append(",");
            csv.append(vendor.getEmail()).append(",");
            csv.append(vendor.getStatus()).append(",");
            csv.append(vendor.getRiskScore()).append("\n");
        });

        return csv.toString();
    }

    // ==========================================
    // Scheduler methods (used Day 7)
    // ==========================================
    public void processOverdueVendors() {
        System.out.println("Processing overdue vendors...");
    }

    public void processUpcomingDeadlines() {
        System.out.println("Processing upcoming deadlines...");
    }

    public void generateWeeklySummary() {
        System.out.println("Generating weekly summary...");
    }
}