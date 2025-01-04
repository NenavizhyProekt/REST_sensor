package ru.danil.rest_practice.rest_proj.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder({"value", "raining", "sensor"})
public class MeasurementDTO {
    @NotNull
    @DecimalMax(value = "100.0")
    @DecimalMin(value = "-100.0")
    private float value;

    @NotNull
    private boolean isRaining;

    @NotNull
    private SensorDTO sensor;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(boolean raining) {
        isRaining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
