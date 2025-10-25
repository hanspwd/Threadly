package com.threadly.repository;

import com.threadly.model.User;
import com.threadly.util.validators.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final List<User> userList = new ArrayList<>();

    public void save(User user) {
        for (User sUser: userList) {
            if(user.getUserUUID().equals(sUser.getUserUUID()) || user.getEmail().equalsIgnoreCase(sUser.getEmail())) {
                throw new ValidationException("El usuario ya existe.");
            }
        }
        userList.add(user);
    }

    public List<User> findAll() {
        return new ArrayList<>(userList);
    }

    public User findByUUID(String uuid) {
        for (User user : userList) {
            if(user.getUserUUID().equals(uuid)) {
                return user;
            }
        }
        return null;
    }

    public User findByEmail(String email) {
        for(User user: userList) {
            if(user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public void deleteByUUID(String uuid) {
        boolean removed = userList.removeIf(user -> user.getUserUUID().equals(uuid));
        if (!removed) {
            throw new ValidationException("No se encontró el usuario a eliminar.");
        }
    }

    public List<User> findActiveUsers() {
        List<User> result = new ArrayList<>();
        for(User user : userList) {
            if(user.isActive()) {
                result.add(user);
            }
        }
        return result;
    }

}
