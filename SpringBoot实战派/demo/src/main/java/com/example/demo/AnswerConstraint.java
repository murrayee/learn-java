package com.example.demo;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AnswerValidator.class)
public @interface AnswerConstraint {
    String message() default "请输入公司名称";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

