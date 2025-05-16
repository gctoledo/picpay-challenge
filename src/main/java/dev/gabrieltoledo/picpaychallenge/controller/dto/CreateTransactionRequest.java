package dev.gabrieltoledo.picpaychallenge.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

import dev.gabrieltoledo.picpaychallenge.domain.transaction.Transaction;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTransactionRequest {

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than or equal to 0.01")
    private final BigDecimal amount;

    @NotNull(message = "Sender ID is required")
    private final UUID senderId;

    @NotNull(message = "Receiver ID is required")
    private final UUID receiverId;

    public Transaction toDomain() {
        return new Transaction(amount);
    }
}