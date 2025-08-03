package com.practice.catsgram.service;

import com.practice.catsgram.dao.UserDao;
import com.practice.catsgram.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public Optional<User> findUserById(String id) {
        return userDao.findUserById(id);
    }
}