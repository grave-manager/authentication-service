package com.dl.gm.authentication.service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Stream;

class EnumFromValueValidator implements ConstraintValidator<EnumFromValue, CharSequence> {

    private List<String> acceptableValues;

    @Override
    public void initialize(EnumFromValue constraintAnnotation) {
        acceptableValues =  Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return acceptableValues.contains(value.toString());
    }
}
