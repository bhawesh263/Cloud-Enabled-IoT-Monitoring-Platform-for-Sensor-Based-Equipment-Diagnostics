package com.iot.monitoring.service;

import com.iot.monitoring.model.SensorReading;
import com.iot.monitoring.repository.SensorReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReadingService {

    private final SensorReadingRepository repository;
    private final AnomalyDetectionService anomalyDetectionService;

    @Autowired
    public ReadingService(SensorReadingRepository repository, AnomalyDetectionService anomalyDetectionService) {
        this.repository = repository;
        this.anomalyDetectionService = anomalyDetectionService;
    }

    public SensorReading processAndSaveReading(SensorReading reading) {
        if (reading.getTimestamp() == null) {
            reading.setTimestamp(LocalDateTime.now());
        }
        
        anomalyDetectionService.detectAndSetStatus(reading);
        return repository.save(reading);
    }

    public List<SensorReading> getReadingsByEquipment(String equipmentId) {
        return repository.findByEquipmentIdOrderByTimestampDesc(equipmentId);
    }

    public List<SensorReading> getAllReadings() {
        return repository.findAll();
    }
}
