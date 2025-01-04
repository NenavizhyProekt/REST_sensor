package ru.danil.rest_practice.rest_proj.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.danil.rest_practice.rest_proj.exceptions.SensorNotCreatedException;
import ru.danil.rest_practice.rest_proj.exceptions.SensorNotFoundException;
import ru.danil.rest_practice.rest_proj.util.errorEntities.SensorErrorResponse;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalSensorExceptionHandler {
    @ExceptionHandler(SensorNotFoundException.class)
    public ResponseEntity<SensorErrorResponse> handleSensorNotFound(){
        SensorErrorResponse response = new SensorErrorResponse(
                "Sensor not found", LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleSensorNotCreated(SensorNotCreatedException ex){
        SensorErrorResponse response = new SensorErrorResponse(
                ex.getMessage(), LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
