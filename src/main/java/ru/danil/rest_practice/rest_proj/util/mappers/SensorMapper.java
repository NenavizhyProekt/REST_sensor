package ru.danil.rest_practice.rest_proj.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.danil.rest_practice.rest_proj.dto.SensorAndMeasuresDTO;
import ru.danil.rest_practice.rest_proj.dto.SensorDTO;
import ru.danil.rest_practice.rest_proj.models.Sensor;

@Component
public class SensorMapper {
    private final ModelMapper modelMapper;

    public SensorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SensorDTO sensorToDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    public SensorAndMeasuresDTO sensorToSensorMeasuresDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorAndMeasuresDTO.class);
    }

    public Sensor dtoToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
