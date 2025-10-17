package com.threadly.util.validators;

import java.util.regex.Pattern;
import static com.threadly.util.constants.Constant.*;

public class BaseValidator {

    // Guardan el estado del objeto a validar
    private final Object value;
    private final String fieldName;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    /**
     * Constructor privado.
     * Fuerza la creación a través del método estático of().
     */
    private BaseValidator(Object value, String fieldName) {
        this.value = value;
        this.fieldName = fieldName;
    }

    public static BaseValidator of(Object value, String fieldName) {
        return new BaseValidator(value, fieldName);
    }

    // Uso para cualquier tipo de objeto.
    public BaseValidator isNull() {
        if (this.value == null) {
            throw new ValidationException(String.format(ERROR_IS_NULL, this.fieldName));
        }
        return this;
    }

    // Uso para Strings (Válida nulo y si esta vació). Asi no se repite la validación de null.
    public BaseValidator isEmpty() {
        this.isNull();
        if (!(this.value instanceof String)) {
            throw new ValidationException(String.format(NO_STRING_VALUE, this.fieldName));
        }
        if (((String) this.value).isEmpty()) {
            throw new ValidationException(String.format(ERROR_IS_EMPTY, this.fieldName));
        }
        return this;
    }

    public BaseValidator lengthMin(int min) {
        this.isNull();
        String strValue = (String) value;
        if (strValue.length() < min) {
            throw new ValidationException(String.format(ERROR_LENGTH_MIN, this.fieldName, min));
        }
        return this;
    }

    public BaseValidator lengthMax(int max) {
        this.isNull();
        String strValue = (String) value;
        if (strValue.length() > max) {
            throw new ValidationException(String.format(ERROR_LENGTH_MAX, this.fieldName, max));
        }
        return this;
    }

    public BaseValidator isEmail() {
        this.isEmpty();

        String email = (String) value;
        if(!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException(String.format(INVALID_EMAIL_FORMAT, this.fieldName));
        }
        return this;
    }
}
