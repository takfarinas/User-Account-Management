package com.technical.test.validator;

import com.technical.test.resource.UserResource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;


public class UserValidator implements ConstraintValidator<ValidUser, UserResource> {

    public static final String FRANCE = "france";

    @Override
    public boolean isValid(UserResource userResource, ConstraintValidatorContext constraintValidatorContext) {
        Period period = Period.between(userResource.getBirthday(), LocalDate.now());
        return FRANCE.equalsIgnoreCase(userResource.getCountryOfResidence()) && period.getYears() >= 18;
    }
}

