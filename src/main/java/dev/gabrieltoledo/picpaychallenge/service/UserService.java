package dev.gabrieltoledo.picpaychallenge.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.gabrieltoledo.picpaychallenge.adapter.DocumentValidator;
import dev.gabrieltoledo.picpaychallenge.controller.dto.CreateUserRequest;
import dev.gabrieltoledo.picpaychallenge.controller.dto.UserResponse;
import dev.gabrieltoledo.picpaychallenge.domain.user.User;
import dev.gabrieltoledo.picpaychallenge.domain.user.UserType;
import dev.gabrieltoledo.picpaychallenge.exceptions.DuplicateFieldException;
import dev.gabrieltoledo.picpaychallenge.exceptions.InsufficientBalanceException;
import dev.gabrieltoledo.picpaychallenge.exceptions.InvalidDocumentException;
import dev.gabrieltoledo.picpaychallenge.exceptions.UnauthorizedException;
import dev.gabrieltoledo.picpaychallenge.exceptions.UserNotFoundException;
import dev.gabrieltoledo.picpaychallenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final DocumentValidator documentValidator;

    public void validateTransaction(User sender, BigDecimal amount) {
        if (sender.getUserType() != UserType.PERSONAL) {
            throw new UnauthorizedException("Only personal users can make transactions");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }
    }

    public User findUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
    }

    public UserResponse createUser(CreateUserRequest user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(nullUser -> {
            throw new DuplicateFieldException("Email");
        });

        userRepository.findByDocument(user.getDocument()).ifPresent(nullUser -> {
            throw new DuplicateFieldException("Document");
        });

        boolean documentIsValid = documentValidator.isValid(user.getDocument(), user.getUserType());

        if (!documentIsValid) {
            throw new InvalidDocumentException();
        }

        User domain = user.toDomain();

        User saved = userRepository.save(domain);

        return UserResponse.build(saved);
    }

    public void update(User user) {
        Optional<User> existingEmail = userRepository.findByEmail(user.getEmail());

        if (existingEmail.isPresent() && !existingEmail.get().getId().equals(user.getId())) {
            throw new DuplicateFieldException("Email");
        }

        Optional<User> existingDocument = userRepository.findByDocument(user.getDocument());

        if (existingDocument.isPresent() && !existingDocument.get().getId().equals(user.getId())) {
            throw new DuplicateFieldException("Document");
        }

        boolean documentIsValid = documentValidator.isValid(user.getDocument(), user.getUserType());

        if (!documentIsValid) {
            throw new InvalidDocumentException();
        }

        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
