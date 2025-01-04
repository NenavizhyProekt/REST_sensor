package ru.danil.rest_practice.rest_proj.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danil.rest_practice.rest_proj.exceptions.SensorNotFoundException;
import ru.danil.rest_practice.rest_proj.models.Measurement;
import ru.danil.rest_practice.rest_proj.models.Sensor;
import ru.danil.rest_practice.rest_proj.repositories.MeasurementRepository;
import ru.danil.rest_practice.rest_proj.repositories.SensorRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<Measurement> getMeasurements() {
        return measurementRepository.findAll();
    }

    public Measurement getMeasurement(int id){
        return measurementRepository.findById(id)
                .orElse(null);
    }

    @Transactional
    public void save(Measurement measurement) {
        Sensor sensor = measurement.getSensor();
        sensor = sensorRepository.findByName(sensor.getName()).orElseThrow(
                SensorNotFoundException::new
        );

        measurement.setSensor(sensor);
        measurementRepository.save(measurement);
    }
}
