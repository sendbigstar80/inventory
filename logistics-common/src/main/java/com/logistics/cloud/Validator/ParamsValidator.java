package com.logistics.cloud.Validator;

import com.logistics.cloud.constants.RegularConstants;
import com.logistics.cloud.tools.RegexTools;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ParamsValidator implements ConstraintValidator<PhoneValidator, String> {

    private boolean required = false;

    @Override
    public void initialize(PhoneValidator constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return RegexTools.regCheck(value, RegularConstants.PHONE_REGEXP);
    }
}
