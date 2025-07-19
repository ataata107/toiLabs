package com.toilabs.health.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "sensor_data")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id")
    private String deviceId;

    private long timestamp;

    @Column(name = "hydration_level")
    private double hydrationLevel;

    @Column(name = "bowel_frequency")
    private int bowelFrequency;

    @Column(name = "image_base64")
    private String imageBase64; // optional

    private String prediction;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getHydrationLevel() {
        return hydrationLevel;
    }

    public void setHydrationLevel(double hydrationLevel) {
        this.hydrationLevel = hydrationLevel;
    }

    public int getBowelFrequency() {
        return bowelFrequency;
    }

    public void setBowelFrequency(int bowelFrequency) {
        this.bowelFrequency = bowelFrequency;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
