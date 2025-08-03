package com.practice.catsgram.controller;

import com.practice.catsgram.model.User;
import com.practice.catsgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{login}")
    public Optional<User> getUser(@PathVariable String login) {
        return userService.findUserById(login);
    }
}
