package com.threadly.service;

import com.threadly.model.Post;
import com.threadly.model.User;
import com.threadly.util.validators.UserValidator;
import java.time.LocalDateTime;

public class UserService {

    public void deactivateAccount(User user) {
        UserValidator.validateCanDeactivate(user);
        user.setActive(false);
        user.setDeactivationDate(LocalDateTime.now());
        user.setReactivationDate(null);
    }

    public void reactivateAccount(User user) {
        UserValidator.validateCanReactivate(user);
        user.setActive(true);
        user.setReactivationDate(LocalDateTime.now());
        user.setDeactivationDate(null);
    }

    public void publishPost(User user, Post post) {
        UserValidator.validateIsActive(user);
        // IMPLEMENTAR PRONTAMENTE LOGICA DE NEGOCIO RESTANTE
    }

    public void comment(User user, Post post, String content) {
        UserValidator.validateIsActive(user);
        // IMPLEMENTAR PRONTAMENTE LOGICA DE NEGOCIO RESTANTE
    }

}
