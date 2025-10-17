package com.threadly.model;

import com.threadly.util.validators.UserValidator;
import com.threadly.util.validators.ValidationException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
public abstract class User {

    private String UUID;
    private String username;
    private String email;
    private LocalDateTime registrationDate;
    private LocalDateTime deactivationDate = null;
    private LocalDateTime reactivationDate = null;
    private boolean active;

    public void autoSetUUID() {
        UUID uuid = java.util.UUID.randomUUID();
        String uuidStr = uuid.toString();

        UserValidator.uuidValidator(uuidStr);

        this.UUID = uuid.toString();
    }

    public void setUsername(String username) {
        UserValidator.usernameValidator(username);
        this.username = username;
    }

    public void setEmail(String email) {
        UserValidator.emailValidator(email);
        this.email = email;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public abstract void publishPost(Category category, String title, String content);

    public abstract void comment(Post post, String content);

    //ForumService debera verificar si la acc esta desactiva para poder realizar post, comments, etc.
    public void desactivateAccount() {
        if (!active) {
            throw new ValidationException("User is already desactivated.");
        }
        active = false;
        reactivationDate = null;
        deactivationDate = LocalDateTime.now();
    }

    public void reactiveteAccount() {
        if (active) {
            throw new ValidationException("User is already activated.");
        }
        active = true;
        deactivationDate = null;
        reactivationDate = LocalDateTime.now();
    }
}
