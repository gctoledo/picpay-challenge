package dev.gabrieltoledo.picpaychallenge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.gabrieltoledo.picpaychallenge.exceptions.DuplicateFieldException;
import dev.gabrieltoledo.picpaychallenge.exceptions.InsufficientBalanceException;
import dev.gabrieltoledo.picpaychallenge.exceptions.UnauthorizedException;
import dev.gabrieltoledo.picpaychallenge.exceptions.UserNotFoundException;
import dev.gabrieltoledo.picpaychallenge.exceptions.dto.ErrorResponse;
import dev.gabrieltoledo.picpaychallenge.exceptions.dto.FieldErrors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();

        List<FieldErrors> errorsList = fieldErrors
                .stream()
                .map(fe -> new FieldErrors(fe.getField(), fe.getDefaultMessage()))
                .toList();

        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error", errorsList);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleUnauthorizedException(UnauthorizedException e) {
        return new ErrorResponse(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleInsufficientBalanceException(InsufficientBalanceException e) {
        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundExceptions(UserNotFoundException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(DuplicateFieldException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicateFieldException(DuplicateFieldException e) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUntreatedException(RuntimeException e) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
    }

}
