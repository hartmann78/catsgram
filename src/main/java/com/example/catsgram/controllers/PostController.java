package com.example.catsgram.controllers;

import com.example.catsgram.model.Post;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    private Map<Integer, Post> posts = new HashMap<>();

    @GetMapping
    public Collection<Post> findAll() {
        return posts.values();
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        posts.put(post.hashCode(), post);
        return post;
    }
}
