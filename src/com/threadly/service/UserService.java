package com.threadly.service;

import com.threadly.model.User;
import com.threadly.repository.UserRepository;
import com.threadly.util.constants.FieldName;
import com.threadly.util.validators.BaseValidator;
import com.threadly.util.validators.ValidationException;

import java.time.LocalDateTime;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        BaseValidator.of(user, FieldName.USERNAME).isNull();

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new ValidationException("Ya existe un usuario con ese correo electrónico.");
        }

        user.setActive(true);
        user.setRegistrationDate(LocalDateTime.now());
        user.setDeactivationDate(null);
        user.setReactivationDate(null);

        userRepository.save(user);
    }

    public void deactivateAccount(String userUUID) {
        User user = userRepository.findByUUID(userUUID);
        if (user == null) {
            throw new ValidationException("Usuario no encontrado.");
        }

        if (!user.isActive()) {
            throw new ValidationException("El usuario ya está desactivado.");
        }

        user.setActive(false);
        user.setDeactivationDate(LocalDateTime.now());
        user.setReactivationDate(null);
    }

    public void reactivateAccount(String userUUID) {
        User user = userRepository.findByUUID(userUUID);
        if (user == null) {
            throw new ValidationException("Usuario no encontrado.");
        }

        if (user.isActive()) {
            throw new ValidationException("El usuario ya está activo.");
        }

        user.setActive(true);
        user.setReactivationDate(LocalDateTime.now());
        user.setDeactivationDate(null);
    }

    public User getUserByUUID(String uuid) {
        User user = userRepository.findByUUID(uuid);
        if (user == null) {
            throw new ValidationException("Usuario no encontrado.");
        }
        return user;
    }

    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    public List<User> listActiveUsers() {
        return userRepository.findActiveUsers();
    }

    public void deleteUser(String uuid) {
        userRepository.deleteByUUID(uuid);
    }
}
