package com.fintech.creditscoring.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String to, String subject, String body) {
        // For now, just log the email content
        System.out.println("Sending email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);

        // In a real application, integrate with an email service (e.g., SendGrid, AWS SES).
    }
}