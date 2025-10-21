package com.threadly.service;

import com.threadly.model.Post;
import com.threadly.model.User;
import com.threadly.util.validators.UserValidator;

public class CommentService {

    public void comment(User user, Post post, String content) {
        UserValidator.validateIsActive(user);
        // IMPLEMENTAR PRONTAMENTE LOGICA DE NEGOCIO RESTANTE
    }
}
