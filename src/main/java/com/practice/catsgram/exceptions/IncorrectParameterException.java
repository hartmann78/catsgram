package com.practice.catsgram.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IncorrectParameterException extends RuntimeException {
    private final String parameter;
}
