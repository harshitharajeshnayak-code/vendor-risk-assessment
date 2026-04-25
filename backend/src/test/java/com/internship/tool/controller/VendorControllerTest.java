package com.internship.tool.controller;

import com.internship.tool.service.VendorService;

import main.java.com.internship.tool.controller.VendorController;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VendorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendorService vendorService;

    // ==========================================
    // Test GET all vendors endpoint
    // ==========================================
    @Test
    void testGetAllVendors() throws Exception {

        mockMvc.perform(get("/api/vendors/all"))
                .andExpect(status().isOk());
    }

    // ==========================================
    // Test stats endpoint
    // ==========================================
    @Test
    void testGetStats() throws Exception {

        mockMvc.perform(get("/api/vendors/stats"))
                .andExpect(status().isOk());
    }

    // ==========================================
    // Test CSV export endpoint
    // ==========================================
    @Test
    void testExportCsv() throws Exception {

        mockMvc.perform(get("/api/vendors/export"))
                .andExpect(status().isOk());
    }

    // ==========================================
    // Test search endpoint
    // ==========================================
    @Test
    void testSearchVendor() throws Exception {

        mockMvc.perform(get("/api/vendors/search?q=test"))
                .andExpect(status().isOk());
    }
}