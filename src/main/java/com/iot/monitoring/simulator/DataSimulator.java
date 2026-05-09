package com.iot.monitoring.simulator;

import com.iot.monitoring.model.SensorReading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Component
@ConditionalOnProperty(name = "simulator.enabled", havingValue = "true", matchIfMissing = true)
public class DataSimulator {

    private static final Logger logger = LoggerFactory.getLogger(DataSimulator.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();

    @Value("${server.port:8080}")
    private String serverPort;

    private final String[] equipmentIds = {"EQ-001", "EQ-002", "EQ-003"};

    @Scheduled(fixedRateString = "${simulator.rate:5000}")
    public void generateAndSendReading() {
        String equipmentId = equipmentIds[random.nextInt(equipmentIds.length)];
        
        // Generate values with occasional anomalies
        double temperature = 60.0 + (random.nextDouble() * 30.0);
        if (random.nextInt(10) > 8) temperature += 30.0; // Anomaly

        double vibration = 2.0 + (random.nextDouble() * 3.0);
        if (random.nextInt(10) > 8) vibration += 5.0; // Anomaly

        double pressure = 100.0 + (random.nextDouble() * 40.0);
        if (random.nextInt(10) > 8) pressure += 70.0; // Anomaly

        SensorReading reading = new SensorReading(equipmentId, null, temperature, vibration, pressure);

        String url = "http://localhost:" + serverPort + "/api/readings";
        try {
            restTemplate.postForObject(url, reading, SensorReading.class);
            logger.info("Sent reading for {}: Temp={}, Vib={}, Press={}", 
                    equipmentId, String.format("%.2f", temperature), 
                    String.format("%.2f", vibration), String.format("%.2f", pressure));
        } catch (Exception e) {
            logger.error("Failed to send reading to API: {}", e.getMessage());
        }
    }
}
