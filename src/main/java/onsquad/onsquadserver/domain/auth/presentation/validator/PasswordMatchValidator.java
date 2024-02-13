package onsquad.onsquadserver.domain.auth.presentation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordValidator, PasswordComparator> {

    @Override
    public boolean isValid(PasswordComparator value, ConstraintValidatorContext context) {
        return value.compare();
    }
}
