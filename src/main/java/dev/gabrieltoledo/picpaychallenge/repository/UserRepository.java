package dev.gabrieltoledo.picpaychallenge.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gabrieltoledo.picpaychallenge.domain.user.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByDocument(String document);
    
}
