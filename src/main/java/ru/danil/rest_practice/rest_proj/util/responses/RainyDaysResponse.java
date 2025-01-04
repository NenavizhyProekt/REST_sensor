package ru.danil.rest_practice.rest_proj.util.responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ru.danil.rest_practice.rest_proj.dto.MeasurementDTO;

import java.util.List;

@JsonPropertyOrder({"rainyDaysQuantity", "measurements"})
public class RainyDaysResponse {
    private int rainyDaysQuantity;
    private List<MeasurementDTO> measurements;

    public RainyDaysResponse(List<MeasurementDTO> measurements) {
        this.rainyDaysQuantity = measurements.size();
        this.measurements = measurements;
    }

    public int getRainyDaysQuantity() {
        return rainyDaysQuantity;
    }

    public void setRainyDaysQuantity(int rainyDaysQuantity) {
        this.rainyDaysQuantity = rainyDaysQuantity;
    }

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
