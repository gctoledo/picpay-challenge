package dev.gabrieltoledo.picpaychallenge.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
    
}
