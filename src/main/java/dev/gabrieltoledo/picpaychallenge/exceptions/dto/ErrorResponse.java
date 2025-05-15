package dev.gabrieltoledo.picpaychallenge.exceptions.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private int status;
    private String message;
    private List<FieldErrors> errors;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(int status, String message, List<FieldErrors> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
