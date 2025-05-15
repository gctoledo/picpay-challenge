package dev.gabrieltoledo.picpaychallenge.exceptions;

public class DuplicateFieldException extends RuntimeException {
    public DuplicateFieldException(String field) {
        super(field + " already exists");
    }
    
}
