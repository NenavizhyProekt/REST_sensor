package ru.danil.rest_practice.rest_proj.util.validators;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.danil.rest_practice.rest_proj.dto.SensorDTO;
import ru.danil.rest_practice.rest_proj.repositories.SensorRepository;

@Component
public class SensorDTOValidator implements Validator {
    private final SensorRepository sensorRepository;

    public SensorDTOValidator(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;

        if(sensorRepository.findByName(sensorDTO.getName()).isPresent())
            errors.rejectValue("name", "", "Sensor with such name already exists");
    }
}
