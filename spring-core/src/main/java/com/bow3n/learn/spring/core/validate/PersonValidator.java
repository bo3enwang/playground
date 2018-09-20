package com.bow3n.learn.spring.core.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author bowen
 */
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        Person p = (Person) target;
        if (p.getAge() < 0) {
            errors.rejectValue("age", "negativevalue");
        } else if (p.getAge() > 110) {
            errors.rejectValue("age", "too.darn.old");
        }
    }
}
