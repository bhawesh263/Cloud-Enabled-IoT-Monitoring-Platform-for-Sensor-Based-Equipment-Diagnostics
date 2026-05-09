package com.iot.monitoring.controller;

import com.iot.monitoring.model.SensorReading;
import com.iot.monitoring.service.ReadingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readings")
public class ReadingController {

    private final ReadingService readingService;

    @Autowired
    public ReadingController(ReadingService readingService) {
        this.readingService = readingService;
    }

    @PostMapping
    public ResponseEntity<SensorReading> submitReading(@Valid @RequestBody SensorReading reading) {
        SensorReading savedReading = readingService.processAndSaveReading(reading);
        return new ResponseEntity<>(savedReading, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SensorReading>> getAllReadings() {
        return ResponseEntity.ok(readingService.getAllReadings());
    }

    @GetMapping("/{equipmentId}")
    public ResponseEntity<List<SensorReading>> getReadingsByEquipment(@PathVariable String equipmentId) {
        return ResponseEntity.ok(readingService.getReadingsByEquipment(equipmentId));
    }
}
