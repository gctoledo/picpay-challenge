package dev.gabrieltoledo.picpaychallenge.controller.dto;

import java.math.BigDecimal;
import java.time.Instant;

import dev.gabrieltoledo.picpaychallenge.domain.transaction.Transaction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponse {
    private Long id;
    private BigDecimal amount;
    private UserResponse sender;
    private UserResponse receiver;
    private Instant createdAt;

    public static TransactionResponse build(Transaction transaction) {
        TransactionResponse transactionResponse = new TransactionResponse();

        transactionResponse.setId(transaction.getId());
        transactionResponse.setAmount(transaction.getAmount());
        transactionResponse.setSender(UserResponse.build(transaction.getSender()));
        transactionResponse.setReceiver(UserResponse.build(transaction.getReceiver()));
        transactionResponse.setCreatedAt(transaction.getCreatedAt());

        return transactionResponse;
    }
}
