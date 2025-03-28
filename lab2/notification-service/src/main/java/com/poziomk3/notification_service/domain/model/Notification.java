package com.poziomk3.notification_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class Notification {
    private final String to;                 // recipient email
    private final String templatePath;       // e.g. welcome/en.html
}
