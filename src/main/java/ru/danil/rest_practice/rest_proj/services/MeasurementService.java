package ru.danil.rest_practice.rest_proj.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danil.rest_practice.rest_proj.models.Measurement;
import ru.danil.rest_practice.rest_proj.repositories.MeasurementRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> getMeasurements() {
        return measurementRepository.findAll();
    }
}
