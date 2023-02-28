package com.assignment.spring.api.exception;

public class OpenWeatherNotFoundException extends RuntimeException {

    public OpenWeatherNotFoundException(String message) {
        super(message+" not found");
    }
}