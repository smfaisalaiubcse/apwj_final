package com.management.supershop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    @Before("execution(* com.management.supershop.controller..*(..)) || " +
            "execution(* com.management.supershop.service..*(..)) || " +
            "execution(* com.management.supershop.repository..*(..))")
    public void logAccess(JoinPoint joinPoint) {
        String timestamp = LocalDateTime.now().toString();
        String methodName = joinPoint.getSignature().toString();
        String username = "Anonymous"; // Replace with actual username retrieval logic from context if available

        String logMessage = String.format("User: %s accessed Method: %s at %s", username, methodName, timestamp);
        LOGGER.log(Level.INFO, logMessage);

        // Optional: Save log to a file
        appendToLogFile(logMessage);
    }

    private void appendToLogFile(String logMessage) {
        // Simplified example of file logging (replace with more robust implementation)
        try (java.io.FileWriter fw = new java.io.FileWriter("app.log", true)) {
            fw.write(logMessage + System.lineSeparator());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error writing to log file", e);
        }
    }
}