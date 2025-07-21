package com.toilabs.health.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toilabs.health.dto.SensorData;
import com.toilabs.health.service.ProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SensorDataConsumer {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ProcessingService processingService;

    @KafkaListener(topics = "sensor.health.raw", groupId = "toi-health")
    public void listen(String message) {
        try {
            SensorData data = mapper.readValue(message, SensorData.class);
            processingService.process(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
