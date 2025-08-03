package com.example.catsgram.service;

import com.example.catsgram.exception.InvalidEmailException;
import com.example.catsgram.exception.UserAlreadyExistException;
import com.example.catsgram.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private Map<Integer, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }

    public User findUserByEmail(String email) {
        if (email == null) {
            return null;
        }
        return users.get(email.hashCode());
    }

    public User create(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("В переданных данных отсутствует адрес электронной почты!");
        }
        if (users.containsKey(user.hashCode())) {
            throw new UserAlreadyExistException("Пользователь с данным email'ом существует");
        }
        users.put(user.hashCode(), user);
        return user;
    }

    public User update(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("В переданных данных отсутствует адрес электронной почты!");
        }
        users.replace(user.hashCode(), user);
        return user;
    }
}
