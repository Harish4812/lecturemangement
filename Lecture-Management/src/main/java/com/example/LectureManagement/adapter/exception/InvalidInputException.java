package com.example.LectureManagement.adapter.exception;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException (String message){
        super(message);
    }
}