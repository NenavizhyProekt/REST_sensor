package ru.danil.rest_practice.rest_proj.dto;

import ru.danil.rest_practice.rest_proj.models.Sensor;

public class MeasurementDTO {
    private float value;
    private boolean isRaining;

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
}
