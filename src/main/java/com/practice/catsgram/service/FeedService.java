package com.practice.catsgram.service;

import com.practice.catsgram.dao.FollowDao;
import com.practice.catsgram.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FollowDao followDao;

    public List<Post> getFeedFor(String userId, int max) {
        return followDao.getFollowFeed(userId, max);
    }
}
