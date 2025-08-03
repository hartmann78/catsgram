package com.practice.catsgram.controller;

import com.practice.catsgram.exceptions.IncorrectParameterException;
import com.practice.catsgram.model.Post;
import com.practice.catsgram.service.PostService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.practice.catsgram.utils.Constants.SORTS;

@RestController
@RequestMapping("/feed/friends")
@RequiredArgsConstructor
public class PostFeedController {

    private final PostService postService;

    @PostMapping
    List<Post> getFriendsFeed(@RequestBody FeedParams feedParams) {
        if (!SORTS.contains(feedParams.getSort())) {
            throw new IncorrectParameterException("sort");
        }
        if (feedParams.getSize() == null || feedParams.getSize() <= 0) {
            throw new IncorrectParameterException("size");
        }
        if (feedParams.getFriendsEmails().isEmpty()) {
            throw new IncorrectParameterException("friendsEmails");
        }

        List<Post> result = new ArrayList<>();
        for (String friendEmail : feedParams.getFriendsEmails()) {
            result.addAll(postService.findAllByUserEmail(friendEmail, feedParams.getSize(), feedParams.getSort()));
        }
        return result;
    }

    @Setter
    @Getter
    private static class FeedParams {
        private String sort;
        private Integer size;
        private List<String> friendsEmails;
    }
}
