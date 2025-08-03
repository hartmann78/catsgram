package com.practice.catsgram.dao.impl;

import com.practice.catsgram.dao.FollowDao;
import com.practice.catsgram.dao.PostDao;
import com.practice.catsgram.dao.UserDao;
import com.practice.catsgram.model.Follow;
import com.practice.catsgram.model.Post;
import com.practice.catsgram.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FollowDaoImpl implements FollowDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    private final PostDao postDao;

    @Override
    public List<Post> getFollowFeed(String userId, int max) {
        // получаем все подписки пользователя
        String sql = "select * from cat_follow where follower_id = ?";
        List<Follow> follows = jdbcTemplate.query(sql, (rs, rowNum) -> makeFollow(rs), userId);

        // выгружаем авторов на которых подписан пользователь
        Set<User> authors = follows.stream()
                .map(Follow::getAuthor)
                .map(userDao::findUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        if (authors.isEmpty()) {
            return Collections.emptyList();
        }

        // выгружаем посты полученных выше авторов
        return authors.stream()
                .map(postDao::findPostsByUser)
                .flatMap(Collection::stream)
                // сортируем от новых к старым
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                // отбрасываем лишнее
                .limit(max)
                .collect(Collectors.toList());
    }

    private Follow makeFollow(ResultSet rs) throws SQLException {
        return new Follow(rs.getString("author_id"), rs.getString("follower_id"));
    }
}

