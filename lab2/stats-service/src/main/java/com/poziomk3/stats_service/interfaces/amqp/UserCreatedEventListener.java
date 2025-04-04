package com.poziomk3.stats_service.interfaces.amqp;

import com.poziomk3.dto.UserCreatedEvent;
import com.poziomk3.stats_service.app.event.UserCreatedEventHandler;
import com.poziomk3.stats_service.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserCreatedEventListener {

    private final UserCreatedEventHandler handler;

    public UserCreatedEventListener(UserCreatedEventHandler handler) {
        this.handler = handler;
    }

    @RabbitListener(queues = RabbitMQConfig.USER_CREATED_QUEUE)
    public void handle(UserCreatedEvent event) {
        log.info("Received UserCreatedEvent in stats service for userId: {}, email: {}", event.getUserId(), event.getEmail());
        handler.handle(event);
        log.info("Handled UserCreatedEvent for userId: {}", event.getUserId());
    }
}
