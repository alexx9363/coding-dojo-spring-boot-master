package com.assignment.spring.api.exception;

public class OpenWeatherUnexpectedException extends RuntimeException {

    public OpenWeatherUnexpectedException() {
        super("Something went wrong");
    }
}