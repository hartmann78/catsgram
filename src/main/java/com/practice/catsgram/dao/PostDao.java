package com.practice.catsgram.dao;

import com.practice.catsgram.model.Post;
import com.practice.catsgram.model.User;

import java.util.Collection;

public interface PostDao {
    Collection<Post> findPostsByUser(User user);
}
