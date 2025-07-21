package com.toilabs.health.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toilabs.health.dto.SensorData;
import com.toilabs.health.service.ProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SensorDataConsumer {

    // ObjectMapper is thread-safe — reuse it!
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ProcessingService processingService;

    @KafkaListener(
        topics = "sensor.health.raw",
        groupId = "toi-health",
        // Optionally add concurrency here or in container factory configuration
        // concurrency = "4"
    )
    public void listen(String message) {
        try {
            SensorData data = mapper.readValue(message, SensorData.class);
            processingService.process(data);
        } catch (Exception e) {
            e.printStackTrace();
            // Consider logging properly instead of printing in production
        }
    }
}
