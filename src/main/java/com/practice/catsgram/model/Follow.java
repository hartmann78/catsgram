package com.practice.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Follow {
    private String author;
    private String follower;
}
