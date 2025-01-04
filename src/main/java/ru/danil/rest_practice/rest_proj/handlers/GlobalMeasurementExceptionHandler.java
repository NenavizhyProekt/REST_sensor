package ru.danil.rest_practice.rest_proj.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.danil.rest_practice.rest_proj.exceptions.MeasurementNotFoundException;
import ru.danil.rest_practice.rest_proj.exceptions.SensorNotCreatedException;
import ru.danil.rest_practice.rest_proj.util.responses.MeasurementErrorResponse;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalMeasurementExceptionHandler {
    @ExceptionHandler(MeasurementNotFoundException.class)
    public ResponseEntity<MeasurementErrorResponse> handleSensorNotFound(){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                "Measurement not found", LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handleSensorNotCreated(SensorNotCreatedException ex){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                ex.getMessage(), LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
