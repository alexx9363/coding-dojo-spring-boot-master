package com.assignment.spring.infrastructure.api.exception;

public class OpenWeatherUnexpectedException extends RuntimeException {

    public OpenWeatherUnexpectedException() {
        super("Something went wrong");
    }
}