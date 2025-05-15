package dev.gabrieltoledo.picpaychallenge.controller.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import dev.gabrieltoledo.picpaychallenge.domain.user.User;
import dev.gabrieltoledo.picpaychallenge.domain.user.UserType;
import lombok.Getter;

@Getter
public class UserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String document;
    private String email;
    private UserType userType;
    private BigDecimal balance;
    private Instant createdAt;


    public static UserResponse build(User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.id = user.getId();
        userResponse.firstName = user.getFirstName();
        userResponse.lastName = user.getLastName();
        userResponse.document = user.getDocument();
        userResponse.email = user.getEmail();
        userResponse.userType = user.getUserType();
        userResponse.balance = user.getBalance();
        userResponse.createdAt = user.getCreatedAt();

        return userResponse;
    }
}
