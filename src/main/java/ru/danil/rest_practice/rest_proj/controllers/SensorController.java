package ru.danil.rest_practice.rest_proj.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.danil.rest_practice.rest_proj.dto.SensorAndMeasuresDTO;
import ru.danil.rest_practice.rest_proj.dto.SensorDTO;
import ru.danil.rest_practice.rest_proj.models.Sensor;
import ru.danil.rest_practice.rest_proj.services.SensorService;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    public SensorController(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<SensorDTO> getAllSensors() {
        return sensorService.getSensors().stream().map(this::sensorToDTO).toList();
    }

    @GetMapping("/{id}")
    public SensorAndMeasuresDTO getSensorById(@PathVariable int id) {
        return sensorToSensorMeasuresDTO(sensorService.getSensorById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody SensorDTO sensorDTO) {
        sensorService.saveSensor(dtoToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateSensor(@PathVariable int id, @RequestBody SensorDTO sensorDTO){
        sensorService.updateSensor(id, dtoToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSensor(@PathVariable int id) {
        sensorService.deleteSensorById(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private SensorDTO sensorToDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    private SensorAndMeasuresDTO sensorToSensorMeasuresDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorAndMeasuresDTO.class);
    }

    private Sensor dtoToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
