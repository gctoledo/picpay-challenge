package dev.gabrieltoledo.picpaychallenge.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
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

    public void save(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(nullUser -> {
            throw new DuplicateFieldException("Email");
        });

        userRepository.findByDocument(user.getDocument()).ifPresent(nullUser -> {
            throw new DuplicateFieldException("Document");
        });

        validateDocument(user);

        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    private void validateDocument (User user) {
        try {
            if (user.getUserType() == UserType.PERSONAL) {
                new CPFValidator().assertValid(user.getDocument());
            }

            if (user.getUserType() == UserType.BUSINESS) {
                new CNPJValidator().assertValid(user.getDocument());
            }
        } catch (InvalidStateException e) {
            throw new InvalidDocumentException();
        }
    }
}
