package com.practice.catsgram.dao;

import com.practice.catsgram.model.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findUserById(String id);
}
