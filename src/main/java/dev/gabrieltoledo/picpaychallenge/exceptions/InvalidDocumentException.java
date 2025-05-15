package dev.gabrieltoledo.picpaychallenge.exceptions;

public class InvalidDocumentException extends RuntimeException {
    public InvalidDocumentException() {
        super("Document provided is invalid");
    }
    
}
