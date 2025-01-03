package ru.danil.rest_practice.rest_proj.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.danil.rest_practice.rest_proj.dto.SensorAndMeasuresDTO;
import ru.danil.rest_practice.rest_proj.dto.SensorDTO;
import ru.danil.rest_practice.rest_proj.exceptions.SensorNotCreatedException;
import ru.danil.rest_practice.rest_proj.exceptions.SensorNotFoundException;
import ru.danil.rest_practice.rest_proj.services.SensorService;
import ru.danil.rest_practice.rest_proj.util.errorEntities.SensorErrorResponse;
import ru.danil.rest_practice.rest_proj.util.mappers.SensorMapper;
import ru.danil.rest_practice.rest_proj.util.validators.SensorDTOValidator;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorMapper sensorMapper;
    private final SensorDTOValidator sensorDTOValidator;

    public SensorController(SensorService sensorService, SensorMapper sensorMapper, SensorDTOValidator sensorDTOValidator) {
        this.sensorService = sensorService;
        this.sensorMapper = sensorMapper;
        this.sensorDTOValidator = sensorDTOValidator;
    }

    @GetMapping
    public List<SensorDTO> getAllSensors() {
        return sensorService.getSensors().stream().map(sensorMapper::sensorToDTO).toList();
    }

    @GetMapping("/{id}")
    public SensorAndMeasuresDTO getSensorById(@PathVariable int id) {
        return sensorMapper.sensorToSensorMeasuresDTO(sensorService.getSensorById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerSensor(
            @RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult
    ) {
        validateSensorDTO(sensorDTO, bindingResult);

        sensorService.saveSensor(sensorMapper.dtoToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateSensor(
            @PathVariable int id,
            @RequestBody @Valid SensorDTO sensorDTO,
            BindingResult bindingResult
    ){
        validateSensorDTO(sensorDTO, bindingResult);

        sensorService.updateSensor(id, sensorMapper.dtoToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSensor(@PathVariable int id) {
        sensorService.deleteSensorById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

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


    private void validateSensorDTO(SensorDTO sensorDTO, BindingResult bindingResult) {
        sensorDTOValidator.validate(sensorDTO, bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder errors = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(
                    error -> errors
                            .append(error.getField())
                            .append(" - ")
                            .append(error.getDefaultMessage())
                            .append(";")
            );

            throw new SensorNotCreatedException(errors.toString());
        }
    }

}
