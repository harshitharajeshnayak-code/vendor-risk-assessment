package com.internship.tool.service;

import com.internship.tool.entity.Vendor;
import com.internship.tool.exception.ValidationException;
import com.internship.tool.exception.ResourceNotFoundException;
import com.internship.tool.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    // ✅ CREATE
    public Vendor createVendor(Vendor vendor) {
        validateVendor(vendor);
        return vendorRepository.save(vendor);
    }

    // ✅ GET BY ID
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id: " + id));
    }

    // ✅ GET ALL
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    // ✅ UPDATE
    public Vendor updateVendor(Long id, Vendor vendor) {
        Vendor existing = getVendorById(id);

        validateVendor(vendor);

        existing.setName(vendor.getName());
        existing.setEmail(vendor.getEmail());
        existing.setPhone(vendor.getPhone());

        return vendorRepository.save(existing);
    }

    // ✅ DELETE
    public void deleteVendor(Long id) {
        Vendor existing = getVendorById(id);
        vendorRepository.delete(existing);
    }

    // 🔥 VALIDATION METHOD (IMPORTANT)
    private void validateVendor(Vendor vendor) {

        if (vendor == null) {
            throw new ValidationException("Vendor object cannot be null");
        }

        // Name validation
        if (vendor.getName() == null || vendor.getName().trim().isEmpty()) {
            throw new ValidationException("Vendor name is required");
        }

        // Email validation
        if (vendor.getEmail() == null || !vendor.getEmail().contains("@")) {
            throw new ValidationException("Vendor email must contain '@'");
        }

        // Phone validation
        if (vendor.getPhone() == null || vendor.getPhone().length() < 10) {
            throw new ValidationException("Phone number must be at least 10 digits");
        }
    }
}