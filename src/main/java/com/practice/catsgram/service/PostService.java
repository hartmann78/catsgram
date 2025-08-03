package com.practice.catsgram.service;

import com.practice.catsgram.exceptions.PostNotFoundException;
import com.practice.catsgram.exceptions.UserNotFoundException;
import com.practice.catsgram.model.Post;
import com.practice.catsgram.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserService userService;
    private final List<Post> posts = new ArrayList<>();

    private static Integer globalId = 0;

    public List<Post> findAll(Integer size, Integer from, String sort) {
        return posts.stream()
                .sorted((p0, p1) -> {
                    int comparison = p0.getCreationDate().compareTo(p1.getCreationDate());  // прямой порядок сортировки
                    if ("desc".equals(sort)) {  // обратный порядок сортировки
                        comparison *= -1;
                    }
                    return comparison;
                })
                .skip(from)
                .limit(size)
                .collect(Collectors.toList());
    }

    public Post create(Post post) {
        User postAuthor = userService.findUserByEmail(post.getAuthor());
        if (postAuthor == null) {
            throw new UserNotFoundException(String.format(
                    "Пользователь %s не найден",
                    post.getAuthor()));
        }

        post.setId(getNextId());
        posts.add(post);
        return post;
    }

    public Post findPostById(Integer postId) {
        return posts.stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException(String.format("Пост № %d не найден", postId)));
    }

    public List<Post> findAllByUserEmail(String email, Integer size, String sort) {
        return posts.stream()
                .filter(post -> email.equals(post.getAuthor()))
                .sorted((p0, p1) -> {
                    int comparison = p0.getCreationDate().compareTo(p1.getCreationDate()); // прямой порядок сортировки
                    if ("desc".equals(sort)) { // обратный порядок сортировки
                        comparison *= -1;
                    }
                    return comparison;
                })
                .limit(size)
                .collect(Collectors.toList());
    }

    private static Integer getNextId() {
        return ++globalId;
    }
}