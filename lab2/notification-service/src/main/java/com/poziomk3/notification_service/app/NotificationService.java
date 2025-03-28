package com.poziomk3.notification_service.app;

import com.poziomk3.notification_service.infrastructure.email.EmailNotificationSender;
import com.poziomk3.notification_service.infrastructure.email.EmailService;
import com.poziomk3.notification_service.infrastructure.template.MinioTemplateLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final MinioTemplateLoader templateLoader;
    private final EmailService emailSender;

    public void notifyUserCreated(String email) {
        String template = templateLoader.loadTemplate("welcome.html");
        emailSender.sendEmail(email, "Welcome to the system!", template);
    }

    public void notifyRoleAssigned(String email, String role) {
        String template = templateLoader.loadTemplate("role-assigned.html")
                .replace("{{role}}", role);
        emailSender.sendEmail(email, "New Role Assigned", template);
    }
}
