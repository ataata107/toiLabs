package com.toilabs.health.service;

import com.toilabs.health.dto.SensorData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.toilabs.health.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Map;

@Service
public class ProcessingService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${ml.model.url}")
    private String mlUrl;

    @Autowired
    private SensorDataRepository repo;

    private final SimpMessagingTemplate websocket;

    public ProcessingService(SimpMessagingTemplate websocket) {
        this.websocket = websocket;
    }

    public void process(SensorData data) {
        try {
            Map<String, String> result = restTemplate.postForObject(mlUrl, data, Map.class);

            // if ("ALERT".equals(result.get("result"))) {
            // Add the prediction to the original data
            data.setPrediction(result.get("result"));
            repo.save(data); // ✅ Save to DB

            websocket.convertAndSend("/topic/alerts", data);
            // }

            System.out.println("Stored data for: " + data.getDeviceId());

        } catch (Exception e) {
            System.err.println("Error contacting ML service: " + e.getMessage());
        }
    }
}
