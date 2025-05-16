package dev.gabrieltoledo.picpaychallenge.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.gabrieltoledo.picpaychallenge.controller.dto.CreateTransactionRequest;
import dev.gabrieltoledo.picpaychallenge.domain.transaction.Transaction;
import dev.gabrieltoledo.picpaychallenge.domain.user.User;
import dev.gabrieltoledo.picpaychallenge.exceptions.UnauthorizedException;
import dev.gabrieltoledo.picpaychallenge.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    
    private final UserService userService;
    private final TransactionRepository transactionRepository;
    private final RestTemplate restTemplate;

    public void save(CreateTransactionRequest transaction) {
        User sender = userService.findUserById(transaction.getSenderId());
        User receiver = userService.findUserById(transaction.getReceiverId());

        if (sender.getId().equals(receiver.getId())) {
            throw new IllegalArgumentException("Sender and receiver cannot be the same");
        }

        userService.validateTransaction(sender, transaction.getAmount());

        boolean isAuthorized = authorizeTransaction(sender, transaction.getAmount());

        if (!isAuthorized) {
            throw new UnauthorizedException("Transaction not authorized");
        }

        Transaction transactionToSave = transaction.toDomain();
        transactionToSave.setSender(sender);
        transactionToSave.setReceiver(receiver);

        sender.setBalance(sender.getBalance().subtract(transaction.getAmount()));
        receiver.setBalance(receiver.getBalance().add(transaction.getAmount()));

        transactionRepository.save(transactionToSave);
        userService.update(sender);
        userService.update(receiver);
    }

    public boolean authorizeTransaction(User sender, BigDecimal amount) {
        ResponseEntity<Map> response = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> body = response.getBody();

            if (body != null && body.containsKey("data")) {
                Map<String, Object> data = (Map<String, Object>) body.get("data");

                if (data != null && Boolean.TRUE.equals(data.get("authorization"))) {
                    return true;
                }
            }
        }
        return false;
    }
}
