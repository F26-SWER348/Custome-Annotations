package com.mylecture.exercise8;

public class NotificationManager {
    @Inject
    private EmailService emailService;
    public void notifyUser() {
        emailService.sendEmail("Welcome!");
    }
}

