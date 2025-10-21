package com.threadly.util.validators;

import com.threadly.model.User;
import java.time.LocalDateTime;
import static com.threadly.util.constants.Constant.*;
import static com.threadly.util.constants.FieldName.*;

public class UserValidator {

    public static void uuidValidator(String uuid) {
        BaseValidator.of(uuid, UUID).isEmpty().lengthMin(8);
    }

    public static void usernameValidator(String username) {
        BaseValidator.of(username, USERNAME).isEmpty().lengthMin(2);
    }

    public static void emailValidator(String email) {
        BaseValidator.of(email, EMAIL).isEmail().lengthMin(5).lengthMax(100);
    }

    public static void validateCanDeactivate(User user) {
        if (!user.isActive()) {
            throw new ValidationException(String.format(ALREADY_DESACTIVATED, user.getUsername()));
        }
    }

    public static void validateCanReactivate(User user) {
        if (user.isActive()) {
            throw new ValidationException(String.format(ALREADY_ACTIVATED, user.getUsername()));
        }
    }

    public static void registrationDateValidator(LocalDateTime date) {
        if (date == null) {
            throw new ValidationException(String.format(ERROR_IS_NULL, REGISTRATION_DATE));
        }
        if (date.isAfter(LocalDateTime.now())) {
            throw new ValidationException(String.format(ERROR_DATE_FUTURE, REGISTRATION_DATE));
        }
    }

    // Uso general
    public static void validateIsActive(User user) {
        if (!user.isActive()) {
            throw new ValidationException(String.format(NON_ACTIVE, user.getUsername()));
        }
    }


}
