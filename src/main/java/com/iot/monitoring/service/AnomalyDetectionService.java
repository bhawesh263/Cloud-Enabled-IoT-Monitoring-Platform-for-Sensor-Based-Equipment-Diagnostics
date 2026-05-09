package com.iot.monitoring.service;

import com.iot.monitoring.model.SensorReading;
import org.springframework.stereotype.Service;

@Service
public class AnomalyDetectionService {

    // Define some arbitrary thresholds for the simulation
    private static final double TEMP_WARNING = 80.0;
    private static final double TEMP_CRITICAL = 100.0;
    
    private static final double VIB_WARNING = 5.0;
    private static final double VIB_CRITICAL = 8.0;

    private static final double PRESS_WARNING = 150.0;
    private static final double PRESS_CRITICAL = 200.0;

    public void detectAndSetStatus(SensorReading reading) {
        boolean isCritical = false;
        boolean isWarning = false;

        if (reading.getTemperature() != null) {
            if (reading.getTemperature() >= TEMP_CRITICAL) isCritical = true;
            else if (reading.getTemperature() >= TEMP_WARNING) isWarning = true;
        }

        if (reading.getVibration() != null) {
            if (reading.getVibration() >= VIB_CRITICAL) isCritical = true;
            else if (reading.getVibration() >= VIB_WARNING) isWarning = true;
        }

        if (reading.getPressure() != null) {
            if (reading.getPressure() >= PRESS_CRITICAL) isCritical = true;
            else if (reading.getPressure() >= PRESS_WARNING) isWarning = true;
        }

        if (isCritical) {
            reading.setStatus("CRITICAL");
        } else if (isWarning) {
            reading.setStatus("WARNING");
        } else {
            reading.setStatus("NORMAL");
        }
    }
}
