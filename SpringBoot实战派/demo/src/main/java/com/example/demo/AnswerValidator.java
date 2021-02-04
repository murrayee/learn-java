package com.example.demo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AnswerValidator implements ConstraintValidator<AnswerConstraint, String> {
    @Override
    public void initialize(AnswerConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String answer, ConstraintValidatorContext constraintValidatorContext) {
        if (!answer.equals("思特沃克")) {
            return false;
        }
        return true;
    }
}


