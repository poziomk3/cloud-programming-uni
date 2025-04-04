package com.poziomk3.notification_service.app;

import com.poziomk3.notification_service.infrastructure.email.EmailNotificationSender;
import com.poziomk3.notification_service.infrastructure.email.EmailService;
import com.poziomk3.notification_service.infrastructure.template.MinioTemplateLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final MinioTemplateLoader templateLoader;
    private final EmailService emailSender;

    public void notifyUserCreated(String email) {
        log.info("Preparing welcome email for: {}", email);
        String template = templateLoader.loadTemplate("welcome.html");
        emailSender.sendEmail(email, "Welcome to the system!", template);
        log.info("Welcome email sent to: {}", email);
    }

    public void notifyRoleAssigned(String email, String role) {
        log.info("Preparing role assignment email for: {} with role: {}", email, role);
        String template = templateLoader.loadTemplate("role-assigned.html")
                .replace("{{role}}", role);
        emailSender.sendEmail(email, "New Role Assigned", template);
        log.info("Role assignment email sent to: {} with role: {}", email, role);
    }
}
