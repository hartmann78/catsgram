package com.practice.catsgram.dao;

import com.practice.catsgram.model.Post;

import java.util.List;

public interface FollowDao {
    List<Post> getFollowFeed(String userId, int max);
}
