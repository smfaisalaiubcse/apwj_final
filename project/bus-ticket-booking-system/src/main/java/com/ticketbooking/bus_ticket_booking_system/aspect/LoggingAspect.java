package com.ticketbooking.bus_ticket_booking_system.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Logs BEFORE a method execution
    @Before("execution(* com.ticketbooking.bus_ticket_booking_system..*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        String username = getCurrentUsername();
        String dateTime = LocalDateTime.now().toString();
        String methodName = joinPoint.getSignature().toShortString();

        logger.info("User '{}' accessed '{}' at {}", username, methodName, dateTime);
    }

    // Logs AFTER successful method execution
    @AfterReturning(pointcut = "execution(* com.ticketbooking.bus_ticket_booking_system..*(..))", returning = "result")
    public void logAfterMethodExecution(JoinPoint joinPoint, Object result) {
        String username = getCurrentUsername();
        String dateTime = LocalDateTime.now().toString();
        String methodName = joinPoint.getSignature().toShortString();

        logger.info("User '{}' successfully executed '{}' at {}. Returned: {}", username, methodName, dateTime, result);
    }

    // Logs WHEN an exception is thrown
    @AfterThrowing(pointcut = "execution(* com.ticketbooking.bus_ticket_booking_system..*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String username = getCurrentUsername();
        String dateTime = LocalDateTime.now().toString();
        String methodName = joinPoint.getSignature().toShortString();

        logger.error("User '{}' encountered an error in '{}' at {}. Exception: {}", username, methodName, dateTime, exception.getMessage());
    }

    // Utility method to get the currently authenticated username
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName(); // Fetch the username of the logged-in user
        }
        return "anonymous"; // Return 'anonymous' if no user is authenticated
    }
}