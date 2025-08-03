package com.example.catsgram.service;

import com.example.catsgram.exception.UserNotFoundException;
import com.example.catsgram.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserService userService;
    private List<Post> posts = new ArrayList<>();

    public Collection<Post> findAll() {
        return posts;
    }

    public Post create(Post post) {
        if (userService.findUserByEmail(post.getAuthor()) == null) {
            throw new UserNotFoundException(String.format("Пользователь %s не найден", post.getAuthor()));
        }
        posts.add(post);
        return post;
    }
}
