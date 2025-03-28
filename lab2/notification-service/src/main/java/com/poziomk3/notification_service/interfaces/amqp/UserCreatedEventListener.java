package com.poziomk3.notification_service.interfaces.amqp;

import com.poziomk3.dto.UserCreatedEvent;
import com.poziomk3.notification_service.app.NotificationService;
import com.poziomk3.notification_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedEventListener {

    private final NotificationService notificationService;

    public UserCreatedEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = RabbitMQConfig.USER_CREATED_QUEUE)
    public void handle(UserCreatedEvent event) {
        notificationService.notifyUserCreated(event.getEmail());
    }
}
