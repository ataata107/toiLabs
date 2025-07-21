package com.toilabs.health.controllers;

import com.toilabs.health.dto.SensorData;
import com.toilabs.health.repository.SensorDataRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SensorDataController {

    private final SensorDataRepository repo;

    public SensorDataController(SensorDataRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/latest-per-device")
    public List<SensorData> getLatestPerDevice() {
        return repo.findLatestPerDevice();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }
}
