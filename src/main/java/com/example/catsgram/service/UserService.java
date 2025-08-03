package com.example.catsgram.service;

import com.example.catsgram.exception.InvalidEmailException;
import com.example.catsgram.exception.UserAlreadyExistException;
import com.example.catsgram.exception.UserNotFoundException;
import com.example.catsgram.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private Map<String, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }

    public User findUserByEmail(String email) {
        if (email == null) {
            throw new InvalidEmailException("В переданных данных отсутствует адрес электронной почты!");
        }
        if (!users.containsKey(email)) {
            throw new UserNotFoundException(String.format("Пользователь %s не найден", email));
        }
        return users.get(email);
    }

    public User create(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("В переданных данных отсутствует адрес электронной почты!");
        }
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с данным email'ом существует");
        }
        users.put(user.getEmail(), user);
        return user;
    }

    public User update(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("В переданных данных отсутствует адрес электронной почты!");
        }
        users.replace(user.getEmail(), user);
        return user;
    }
}
