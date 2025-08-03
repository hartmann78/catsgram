package com.example.catsgram.service;

import com.example.catsgram.exception.PostNotFoundException;
import com.example.catsgram.exception.UserNotFoundException;
import com.example.catsgram.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private Integer globalPostId = 0;
    private final UserService userService;
    private Map<Integer, Post> posts = new HashMap<>();

    public Collection<Post> findAll(String sort, Integer from, Integer size) {
        return posts.values()
                .stream()
                .sorted((p0, p1) -> {
                    int comparison = p0.getCreationDate().compareTo(p1.getCreationDate());
                    if (sort.equals("desc")) {
                        comparison *= -1;
                    }
                    return comparison;
                })
                .skip(from)
                .limit(size)
                .collect(Collectors.toList());
    }

    public Post findPostById(Integer postId) {
        if (!posts.containsKey(postId)) {
            throw new PostNotFoundException(String.format("Пост с id %d не найден", postId));
        }
        return posts.get(postId);
    }

    public List<Post> findAllByUserEmail(String email, Integer size, String sort) {
        return posts.values()
                .stream()
                .filter(post -> email.equals(post.getAuthor()))
                .sorted((p0, p1) -> {
                    int comparison = p0.getCreationDate().compareTo(p1.getCreationDate());
                    if (sort.equals("desc")) {
                        comparison *= -1;
                    }
                    return comparison;
                })
                .limit(size)
                .collect(Collectors.toList());
    }

    public Post create(Post post) {
        if (userService.findUserByEmail(post.getAuthor()) == null) {
            throw new UserNotFoundException(String.format("Пользователь %s не найден", post.getAuthor()));
        }
        posts.put(++globalPostId, post);
        return post;
    }
}
