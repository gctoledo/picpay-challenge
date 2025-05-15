package dev.gabrieltoledo.picpaychallenge.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

import dev.gabrieltoledo.picpaychallenge.domain.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTransactionRequest {
    BigDecimal  amount;
    UUID senderId;
    UUID receiverId;

    public Transaction toDomain() {
        return new Transaction(amount);
    }
}
