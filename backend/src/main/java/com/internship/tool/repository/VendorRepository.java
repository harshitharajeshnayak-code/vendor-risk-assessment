package com.internship.tool.repository;

import com.internship.tool.entity.Vendor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    // Search by vendor name or email
    @Query("SELECT v FROM Vendor v WHERE " +
           "LOWER(v.vendorName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(v.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Vendor> searchVendors(String keyword, Pageable pageable);

    // Get only active vendors
    List<Vendor> findByDeletedFalse();

    // Filter by status
    List<Vendor> findByStatus(String status);
}