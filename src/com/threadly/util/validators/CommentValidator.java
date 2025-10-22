package com.threadly.util.validators;

import com.threadly.model.Post;
import com.threadly.model.User;

import java.time.LocalDateTime;

import static com.threadly.util.constants.Constant.*;
import static com.threadly.util.constants.FieldName.*;
import static com.threadly.util.constants.FieldName.UPDATED_AT;

public class CommentValidator {

    public static void uuidValidator(String uuid) {
        BaseValidator.of(uuid, UUID).isEmpty().lengthMin(8);
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
    public static void postValidator(Post post) {
        BaseValidator.of(post, POST).isNull();
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
