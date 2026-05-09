package com.iot.monitoring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_readings")
public class SensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "equipment_id")
    private String equipmentId;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "vibration")
    private Double vibration;

    @Column(name = "pressure")
    private Double pressure;

    @Column(name = "status")
    private String status;

    public SensorReading() {
    }

    public SensorReading(String equipmentId, LocalDateTime timestamp, Double temperature, Double vibration, Double pressure) {
        this.equipmentId = equipmentId;
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.vibration = vibration;
        this.pressure = pressure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getVibration() {
        return vibration;
    }

    public void setVibration(Double vibration) {
        this.vibration = vibration;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
