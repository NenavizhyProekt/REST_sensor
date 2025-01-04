package ru.danil.rest_practice.rest_proj.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.danil.rest_practice.rest_proj.dto.MeasurementDTO;
import ru.danil.rest_practice.rest_proj.models.Measurement;

@Component
public class MeasurementMapper {
    private final ModelMapper modelMapper;

    public MeasurementMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MeasurementDTO measurementToDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    public Measurement dtoToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
