package com.poziomk3.notification_service.infrastructure.email;

import com.poziomk3.notification_service.domain.model.Notification;
import com.poziomk3.notification_service.domain.service.NotificationSender;
import com.poziomk3.notification_service.infrastructure.template.MinioTemplateLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailNotificationSender implements NotificationSender {

    private final JavaMailSender mailSender;
    private final MinioTemplateLoader templateLoader;

    public EmailNotificationSender(JavaMailSender mailSender, MinioTemplateLoader templateLoader) {
        this.mailSender = mailSender;
        this.templateLoader = templateLoader;
    }

    @Override
    public void send(Notification notification) {
        try {
            String html = templateLoader.loadTemplate(notification.getTemplatePath());

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(notification.getTo());
            helper.setSubject("Notification"); // You can make this configurable if needed
            helper.setText(html, true);

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send notification", e);
        }
    }
}
