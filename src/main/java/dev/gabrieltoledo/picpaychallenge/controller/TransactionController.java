package dev.gabrieltoledo.picpaychallenge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrieltoledo.picpaychallenge.controller.dto.CreateTransactionRequest;
import dev.gabrieltoledo.picpaychallenge.controller.dto.TransactionResponse;
import dev.gabrieltoledo.picpaychallenge.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody @Valid CreateTransactionRequest transaction) {
        TransactionResponse createdTransaction = transactionService.createTransaction(transaction);

        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }
}
