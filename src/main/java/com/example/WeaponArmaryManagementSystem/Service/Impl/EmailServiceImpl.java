package com.example.WeaponArmaryManagementSystem.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.WeaponArmaryManagementSystem.Service.EmailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") // Injecting email from application.properties
    private String senderEmail;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String email, String subject, String body) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            helper.setTo(email); // Sending email to the provided address
            helper.setSubject(subject);
            helper.setText(body, true); // true indicates HTML format
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
    }
		
}
