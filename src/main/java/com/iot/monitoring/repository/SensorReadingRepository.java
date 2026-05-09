package com.iot.monitoring.repository;

import com.iot.monitoring.model.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {
    List<SensorReading> findByEquipmentIdOrderByTimestampDesc(String equipmentId);
}
