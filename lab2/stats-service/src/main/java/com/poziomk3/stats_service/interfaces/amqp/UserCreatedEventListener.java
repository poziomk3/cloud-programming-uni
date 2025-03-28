package com.poziomk3.stats_service.interfaces.amqp;

import com.poziomk3.dto.UserCreatedEvent;
import com.poziomk3.stats_service.app.event.UserCreatedEventHandler;
import com.poziomk3.stats_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedEventListener {

    private final UserCreatedEventHandler handler;

    public UserCreatedEventListener(UserCreatedEventHandler handler) {
        this.handler = handler;
    }

    @RabbitListener(queues = RabbitMQConfig.USER_CREATED_QUEUE)
    public void handle(UserCreatedEvent event) {
        handler.handle(event);
    }
}
