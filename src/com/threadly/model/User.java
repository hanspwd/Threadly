package com.threadly.model;

import com.threadly.util.validators.UserValidator;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class User {

    private String userUUID;
    private String username;
    private String email;
    private LocalDateTime registrationDate;
    private LocalDateTime deactivationDate = null;
    private LocalDateTime reactivationDate = null;
    private boolean active;

    private User() {
        this.userUUID = java.util.UUID.randomUUID().toString();
        UserValidator.uuidValidator(userUUID);
    }

    public void setUsername(String username) {
        UserValidator.usernameValidator(username);
        this.username = username;
    }

    public void setEmail(String email) {
        UserValidator.emailValidator(email);
        this.email = email;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        UserValidator.registrationDateValidator(registrationDate);
        this.registrationDate = registrationDate;
    }
}
