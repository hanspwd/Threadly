package com.threadly.service;

import com.threadly.model.Post;
import com.threadly.model.User;
import com.threadly.util.validators.UserValidator;

public class PostService {

    public void publishPost(User user, Post post) {
        UserValidator.validateIsActive(user);
        // IMPLEMENTAR PRONTAMENTE LOGICA DE NEGOCIO RESTANTE
    }
}
