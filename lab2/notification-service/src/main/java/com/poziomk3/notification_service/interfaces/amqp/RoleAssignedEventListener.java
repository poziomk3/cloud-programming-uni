package com.poziomk3.notification_service.interfaces.amqp;

import com.poziomk3.dto.RoleAssignedEvent;
import com.poziomk3.notification_service.app.NotificationService;
import com.poziomk3.notification_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RoleAssignedEventListener {

    private final NotificationService notificationService;

    public RoleAssignedEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = RabbitMQConfig.ROLE_ASSIGNED_QUEUE)
    public void handle(RoleAssignedEvent event) {
        // Assumes only one role, can be extended
        String firstRole = event.getRoles().stream().findFirst().orElse("ROLE_UNKNOWN");
        notificationService.notifyRoleAssigned(event.getEmail(), firstRole);
    }
}
