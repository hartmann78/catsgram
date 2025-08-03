package com.practice.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String email;
    private String nickname;
    private LocalDate birthdate;
}
