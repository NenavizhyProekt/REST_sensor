package ru.danil.rest_practice.rest_proj.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danil.rest_practice.rest_proj.models.Sensor;
import ru.danil.rest_practice.rest_proj.repositories.SensorRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> getSensors() {
        return sensorRepository.findAll();
    }

    public Sensor getSensorById(int id) {
        return sensorRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Transactional
    public void updateSensor(int id, Sensor sensor) {
        sensor.setId(id);
        sensorRepository.save(sensor);
    }

    @Transactional
    public void deleteSensorById(int id) {
        sensorRepository.deleteById(id);
    }
}
