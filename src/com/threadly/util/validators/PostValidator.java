package com.threadly.util.validators;

import com.threadly.model.Category;
import com.threadly.model.User;

import java.time.LocalDateTime;

import static com.threadly.util.constants.Constant.*;
import static com.threadly.util.constants.Constant.ERROR_DATE_FUTURE;
import static com.threadly.util.constants.FieldName.*;

public class PostValidator {

    public static void uuidValidator(String uuid) {
        BaseValidator.of(uuid, UUID).isEmpty().lengthMin(8);
    }

    public static void titleValidator(String title) {
        BaseValidator.of(title, TITLE).isEmpty().lengthMin(6).lengthMax(100);
    }

    public static void contentValidator(String content) {
        BaseValidator.of(content, CONTENT).isEmpty().lengthMin(8).lengthMax(750);
    }

    public static void authorValidator(User author) {
        BaseValidator.of(author, AUTHOR).isNull();
        if(!author.isActive()) {
            throw new ValidationException(String.format(NON_ACTIVE, author.getUsername()));
        }
    }

    public static void categoryValidator(Category category) {
        BaseValidator.of(category, CATEGORY).isNull();
    }

    public static void createdAtValidator(LocalDateTime createdAt) {
        BaseValidator.of(createdAt, CREATED_AT).isNull();

        if (createdAt.isAfter(LocalDateTime.now())) {
            throw new ValidationException(String.format(ERROR_DATE_FUTURE, CREATED_AT));
        }
    }

    public static void updatedAtValidator(LocalDateTime updatedAt, LocalDateTime createdAt) {
        BaseValidator.of(updatedAt, UPDATED_AT).isNull();

        if (updatedAt.isBefore(createdAt)) {
            throw new ValidationException(String.format(ERROR_UPDATED_BEFORE_CREATED, UPDATED_AT));
        }

        if (updatedAt.isAfter(LocalDateTime.now())) {
            throw new ValidationException(String.format(ERROR_DATE_FUTURE, UPDATED_AT));
        }
    }

}
