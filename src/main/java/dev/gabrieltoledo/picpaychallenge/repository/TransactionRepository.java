package dev.gabrieltoledo.picpaychallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gabrieltoledo.picpaychallenge.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
