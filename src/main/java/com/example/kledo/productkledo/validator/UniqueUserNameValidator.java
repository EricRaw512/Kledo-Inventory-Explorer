package com.example.kledo.productkledo.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.kledo.productkledo.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUsername, Object>{

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return !userService.userExist((String) value);
    }

}
