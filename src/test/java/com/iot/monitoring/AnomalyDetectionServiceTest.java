package com.iot.monitoring;

import com.iot.monitoring.model.SensorReading;
import com.iot.monitoring.service.AnomalyDetectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnomalyDetectionServiceTest {

    private AnomalyDetectionService service;

    @BeforeEach
    void setUp() {
        service = new AnomalyDetectionService();
    }

    @Test
    void testNormalReading() {
        SensorReading reading = new SensorReading("EQ-1", null, 50.0, 2.0, 100.0);
        service.detectAndSetStatus(reading);
        assertEquals("NORMAL", reading.getStatus());
    }

    @Test
    void testWarningReading() {
        SensorReading reading = new SensorReading("EQ-1", null, 85.0, 2.0, 100.0);
        service.detectAndSetStatus(reading);
        assertEquals("WARNING", reading.getStatus());
    }

    @Test
    void testCriticalReading() {
        SensorReading reading = new SensorReading("EQ-1", null, 50.0, 9.0, 100.0);
        service.detectAndSetStatus(reading);
        assertEquals("CRITICAL", reading.getStatus());
    }
}
