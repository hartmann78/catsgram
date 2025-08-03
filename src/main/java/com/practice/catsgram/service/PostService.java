package com.practice.catsgram.service;

import com.practice.catsgram.dao.PostDao;
import com.practice.catsgram.exceptions.UserNotFoundException;
import com.practice.catsgram.model.Post;
import com.practice.catsgram.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostDao postDao;
    private final UserService userService;

    public Collection<Post> findPostsByUser(String userId) {
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с идентификатором " + userId + " не найден."));

        return postDao.findPostsByUser(user);
    }

    public Collection<Post> findPostsByUser(String authorId, Integer size, String sort) {
        return findPostsByUser(authorId)
                .stream()
                .sorted((p0, p1) -> {
                    int comparison = p0.getCreationDate().compareTo(p1.getCreationDate()); // прямой порядок сортировки
                    if (sort.equals("desc")) {
                        comparison *= -1; // обратный порядок сортировки
                    }
                    return comparison;
                })
                .limit(size)
                .collect(Collectors.toList());
    }
}