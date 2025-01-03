package ru.danil.rest_practice.rest_proj.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
