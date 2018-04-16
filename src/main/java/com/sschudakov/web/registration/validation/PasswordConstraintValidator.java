package com.sschudakov.web.registration.validation;

import com.google.common.base.Joiner;
import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    public void initialize(ValidPassword constraint) {
    }

    /**
     * Validate password based on rules 8-30 characters, 1 uppercase, 1 digit, 1 special character, no
     * whitespace
     */
    public boolean isValid(String password, ConstraintValidatorContext context) {
        final PasswordValidator validator
                = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30)
                , new UppercaseCharacterRule(1)
                , new DigitCharacterRule(1)
                , new SpecialCharacterRule(1)
                , new WhitespaceRule()));
        final RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context
                .buildConstraintViolationWithTemplate(Joiner.on("\n").join(validator.getMessages(result)))
                .addConstraintViolation();
        return false;


    }
}
