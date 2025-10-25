package com.threadly.model;

import com.threadly.util.validators.UserValidator;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public abstract class User {

    private String userUUID;
    private String username;
    private String email;
    private LocalDateTime registrationDate;
    private LocalDateTime deactivationDate = null;
    private LocalDateTime reactivationDate = null;
    private boolean active = false;

    protected User(String username, String email) {
        this.userUUID = UUID.randomUUID().toString();
        setUsername(username);
        setEmail(email);
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
