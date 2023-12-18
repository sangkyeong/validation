package com.example.validation.validator;

import com.example.validation.annotation.phoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Pattern;

public class phoneNumberValidator implements ConstraintValidator<phoneNumber, String> {

    private String regexp;

    @Override
    public void initialize(phoneNumber constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        boolean rst = java.util.regex.Pattern.matches(value, regexp);

        return rst;
    }
}
