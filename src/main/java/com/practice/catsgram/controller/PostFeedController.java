package com.practice.catsgram.controller;

import com.practice.catsgram.model.Post;
import com.practice.catsgram.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feed")
public class PostFeedController {
    private final FeedService feedService;

    @GetMapping
    List<Post> getFriendsFeed(@RequestParam("userId") String userId, @RequestParam(defaultValue = "10") int max) {
        return feedService.getFeedFor(userId, max);
    }
}
