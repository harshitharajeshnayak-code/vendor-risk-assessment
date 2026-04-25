package com.internship.tool.controller;

import com.internship.tool.entity.Vendor;
import com.internship.tool.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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
        //paginated get all
    @GetMapping("/all")
public Page<Vendor> getAllVendors(

        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir
) {

    Sort sort = sortDir.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    PageRequest pageable = PageRequest.of(page, size, sort);

    return vendorService.getAllVendors(pageable);
}

//csv export 
@GetMapping("/export")
public ResponseEntity<String> exportCsv() {

    String csvData = vendorService.exportVendorsToCsv();

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=vendors.csv")
            .contentType(MediaType.TEXT_PLAIN)
            .body(csvData);
}

}