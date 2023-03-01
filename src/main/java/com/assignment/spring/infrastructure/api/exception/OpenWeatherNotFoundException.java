package com.assignment.spring.infrastructure.api.exception;

public class OpenWeatherNotFoundException extends RuntimeException {

    public OpenWeatherNotFoundException(String message) {
        super(message+" not found");
    }
}