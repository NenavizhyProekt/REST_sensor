package ru.danil.rest_practice.rest_proj.exceptions;

public class SensorNotCreatedException extends RuntimeException {
    public SensorNotCreatedException(String msg) {
        super(msg);
    }
}
