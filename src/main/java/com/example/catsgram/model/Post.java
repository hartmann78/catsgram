package com.example.catsgram.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Post {
    private Integer id;
    private final String author;
    private final LocalDate creationDate = LocalDate.now();
    private String description;
    private String photoUrl;

    public Post(String author, String description, String photoUrl) {
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
    }
}
