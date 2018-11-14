package com.traskindustries.controller;

import com.traskindustries.messages.GetDNAStatsResponse;
import com.traskindustries.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/stats",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping
    public ResponseEntity<GetDNAStatsResponse> getDNAStats() {
        return new ResponseEntity<>(
                statsService
                .getDNAStats(),
                HttpStatus.OK);
    }
}
