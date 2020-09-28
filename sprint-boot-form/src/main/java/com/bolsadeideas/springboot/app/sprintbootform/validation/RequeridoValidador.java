package com.bolsadeideas.springboot.app.sprintbootform.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequeridoValidador implements ConstraintValidator<Requerido,String> {

    @Override
    public void initialize(Requerido constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null || /*value.isEmpty() || value.isBlank()*/  !StringUtils.hasText(value)){ //cuidado al importar , hay varias StringUtils)
            return false;
        }
        return true;
    }
}

