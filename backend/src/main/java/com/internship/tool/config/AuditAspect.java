package com.internship.tool.config;

import com.internship.tool.service.AuditLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    // Inject audit service
    @Autowired
    private AuditLogService auditLogService;

    // ==========================================
    // Intercept create/update/delete methods
    // ==========================================
    @Around("execution(* com.internship.tool.service.*.create*(..)) || " +
            "execution(* com.internship.tool.service.*.update*(..)) || " +
            "execution(* com.internship.tool.service.*.delete*(..))")
    public Object logAudit(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();

        System.out.println("Audit Start: " + methodName);

        // Execute actual service method
        Object result = joinPoint.proceed();

        // Save audit log entry
        auditLogService.saveAudit(
                methodName,
                "Vendor",
                "old json",
                "new json"
        );

        System.out.println("Audit Success: " + methodName);

        return result;
    }
}