package com.poziomk3.stats_service.interfaces.rest;

import com.poziomk3.api.StatsApi;
import com.poziomk3.dto.TotalRegistrationsResponse;
import com.poziomk3.stats_service.app.query.GetTotalRegistrationsQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsApiImpl implements StatsApi {

    private final GetTotalRegistrationsQueryHandler queryHandler;

    public StatsApiImpl(GetTotalRegistrationsQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @Override
    public ResponseEntity<TotalRegistrationsResponse> getTotalRegistrations() {
        var dto = queryHandler.handle(); // Returns RegistrationStats
        return ResponseEntity.ok(dto);
    }
}
