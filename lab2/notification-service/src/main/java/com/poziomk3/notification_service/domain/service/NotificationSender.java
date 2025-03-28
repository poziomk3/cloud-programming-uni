package com.poziomk3.notification_service.domain.service;

import com.poziomk3.notification_service.domain.model.Notification;

public interface NotificationSender {
    void send(Notification notification);
}