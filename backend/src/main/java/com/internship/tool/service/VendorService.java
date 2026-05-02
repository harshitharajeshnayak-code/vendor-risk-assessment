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

    public Vendor createVendor(Vendor vendor) {
        validateVendor(vendor);
        return vendorRepository.save(vendor);
    }

    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id: " + id));
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor updateVendor(Long id, Vendor vendor) {
        Vendor existing = getVendorById(id);

        validateVendor(vendor);

        existing.setName(vendor.getName());
        existing.setEmail(vendor.getEmail());
        existing.setPhone(vendor.getPhone());

        return vendorRepository.save(existing);
    }

    public void deleteVendor(Long id) {
        Vendor existing = getVendorById(id);
        vendorRepository.delete(existing);
    }

    private void validateVendor(Vendor vendor) {
        if (vendor == null) {
            throw new ValidationException("Vendor cannot be null");
        }

        if (vendor.getName() == null || vendor.getName().trim().isEmpty()) {
            throw new ValidationException("Vendor name is required");
        }

        if (vendor.getEmail() == null || !vendor.getEmail().contains("@")) {
            throw new ValidationException("Invalid email format");
        }

        if (vendor.getPhone() == null || vendor.getPhone().length() < 10) {
            throw new ValidationException("Invalid phone number");
        }
    }
}