package com.assignment.spring.infrastructure;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeProvider {

    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
