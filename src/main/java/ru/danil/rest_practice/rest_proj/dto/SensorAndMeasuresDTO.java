package ru.danil.rest_practice.rest_proj.dto;

import java.util.List;

public class SensorAndMeasuresDTO {
    private String name;
    private List<MeasurementDTO> measurements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
