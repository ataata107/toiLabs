package com.toilabs.health.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toilabs.health.dto.SensorData;
import com.toilabs.health.service.ProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SensorDataConsumer {

    private final ExecutorService executor = Executors.newFixedThreadPool(1);

    @Autowired
    private ProcessingService processingService;

    @KafkaListener(topics = "sensor.health.raw", groupId = "toi-health")
    public void listen(String message) {
        executor.submit(() -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                SensorData data = mapper.readValue(message, SensorData.class);
                processingService.process(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
