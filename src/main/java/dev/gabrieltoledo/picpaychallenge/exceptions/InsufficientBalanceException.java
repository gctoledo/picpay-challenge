package dev.gabrieltoledo.picpaychallenge.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Insufficient balance for this transaction");
    }
    
}
