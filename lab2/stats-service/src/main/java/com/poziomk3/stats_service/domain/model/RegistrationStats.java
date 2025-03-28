package com.poziomk3.stats_service.domain.model;


import lombok.Getter;

@Getter
public class RegistrationStats {

    private int totalRegistrations;

    public RegistrationStats(int totalRegistrations) {
        this.totalRegistrations = totalRegistrations;
    }

    public void increment() {
        this.totalRegistrations++;
    }
}
