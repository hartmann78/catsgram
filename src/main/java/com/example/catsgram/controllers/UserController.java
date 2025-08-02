package com.example.catsgram.controllers;

import com.example.catsgram.exceptions.InvalidEmailException;
import com.example.catsgram.exceptions.UserAlreadyExistException;
import com.example.catsgram.model.User;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private Map<Integer, User> users = new HashMap<>();

    @GetMapping
    public Collection<User> findAll() {
        return users.values();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        emailIsValidCheck(user);
        if (!users.isEmpty()) {
            emailExistsCheck(user);
        }
        users.put(user.hashCode(), user);
        return user;
    }

    @PutMapping
    public User update(@RequestBody User user) {
        emailIsValidCheck(user);
        users.replace(user.hashCode(), user);
        return user;
    }

    public void emailIsValidCheck(User user) {
        if (user.getEmail().isBlank()) {
            throw new InvalidEmailException("В переданных данных отсутствует адрес электронной почты!");
        }
    }

    public void emailExistsCheck(User user) {
        users.values().stream()
                .filter(user1 -> user1.getEmail().equals(user.getEmail()))
                .findAny()
                .orElseThrow(() -> new UserAlreadyExistException("Пользователь с данным email'ом существует"));
    }
}
