package com.internship.tool.service;

import org.springframework.stereotype.Service;

@Service
public class AuditLogService {

    public void saveAudit(String action,
                          String entityType,
                          String oldValue,
                          String newValue) {

        System.out.println("Saved audit log -> " + action);
    }
}