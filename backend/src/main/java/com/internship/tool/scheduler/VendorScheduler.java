package com.internship.tool.scheduler;

import com.internship.tool.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VendorScheduler {

    @Autowired
    private VendorService vendorService;

    // ==========================================
    // Runs every day at 9:00 AM
    // Sends overdue reminders
    // ==========================================
    @Scheduled(cron = "0 0 9 * * *")
    public void sendOverdueReminders() {

        System.out.println("Running overdue reminder job...");

        vendorService.processOverdueVendors();
    }

    // ==========================================
    // Runs every day at 10:00 AM
    // Alerts vendors due in next 7 days
    // ==========================================
    @Scheduled(cron = "0 0 10 * * *")
    public void sendUpcomingDeadlineAlerts() {

        System.out.println("Running 7-day alert job...");

        vendorService.processUpcomingDeadlines();
    }

    // ==========================================
    // Runs every Monday at 8:00 AM
    // Weekly summary
    // ==========================================
    @Scheduled(cron = "0 0 8 * * MON")
    public void sendWeeklySummary() {

        System.out.println("Running weekly summary job...");

        vendorService.generateWeeklySummary();
    }
}