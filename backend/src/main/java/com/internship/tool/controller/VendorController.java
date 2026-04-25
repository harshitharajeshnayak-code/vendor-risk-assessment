package com.internship.tool.controller;

import com.internship.tool.entity.Vendor;
import com.internship.tool.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    // ==========================================
    // Update vendor details
    // PUT /api/vendors/{id}
    // ==========================================
    @PutMapping("/{id}")
    public Vendor updateVendor(@PathVariable Long id,
                               @RequestBody Vendor vendor) {

        return vendorService.updateVendor(id, vendor);
    }

    // ==========================================
    // Soft delete vendor
    // DELETE /api/vendors/{id}
    // ==========================================
    @DeleteMapping("/{id}")
    public String deleteVendor(@PathVariable Long id) {

        vendorService.softDeleteVendor(id);
        return "Vendor deleted successfully";
    }

    // ==========================================
    // Search vendors
    // GET /api/vendors/search?q=abc
    // ==========================================
    @GetMapping("/search")
    public List<Vendor> searchVendor(@RequestParam String q) {

        return vendorService.searchVendor(q);
    }

    // ==========================================
    // Dashboard KPI stats
    // GET /api/vendors/stats
    // ==========================================
    @GetMapping("/stats")
    public Map<String, Object> getStats() {

        return vendorService.getDashboardStats();
    }
}