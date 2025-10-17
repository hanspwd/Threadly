package com.threadly.util.validators;

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
}
