package ru.danil.rest_practice.rest_proj.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.danil.rest_practice.rest_proj.dto.MeasurementDTO;
import ru.danil.rest_practice.rest_proj.exceptions.MeasurementNotCreatedException;
import ru.danil.rest_practice.rest_proj.services.MeasurementService;
import ru.danil.rest_practice.rest_proj.util.ValidationErrorFormatter;
import ru.danil.rest_practice.rest_proj.util.mappers.MeasurementMapper;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final MeasurementMapper measurementMapper;

    public MeasurementController(MeasurementService measurementService, MeasurementMapper measurementMapper) {
        this.measurementService = measurementService;
        this.measurementMapper = measurementMapper;
    }

    @GetMapping
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.getMeasurements().stream().map(measurementMapper::measurementToDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasurementDTO> getMeasurementById(@PathVariable int id){
        MeasurementDTO responseBody = measurementMapper.measurementToDTO(measurementService.getMeasurement(id));
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(
            @RequestBody @Valid MeasurementDTO measurementDTO,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            throw new MeasurementNotCreatedException(ValidationErrorFormatter.extractMessageFromErrors(bindingResult));
        }

        measurementService.save(measurementMapper.dtoToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
