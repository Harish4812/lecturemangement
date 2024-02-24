package com.example.LectureManagement.adapter.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ExceptionHandle  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> resourceDoesNotExist(ResourceNotFoundException ex, WebRequest webRequest) {
        return handleExceptionInternal(
                ex,
                new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.getMessage(), "Contact the back-end team for support"),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                webRequest);
    }


    @ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<Object> handleInvalidInput(InvalidInputException ex, WebRequest webRequest) {
        return handleExceptionInternal(
                ex,
                new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ex.getMessage(), "Contact the back-end team for support"),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handleUnAuthorized(AccessDeniedException ex, WebRequest webRequest) {
        return handleExceptionInternal(
                ex,
                new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), ex.getMessage(), "Contact the back-end team for support"),
                new HttpHeaders(),
                HttpStatus.UNAUTHORIZED,
                webRequest);
    }

}
