package dev.gabrieltoledo.picpaychallenge.controller.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import dev.gabrieltoledo.picpaychallenge.domain.user.User;
import dev.gabrieltoledo.picpaychallenge.domain.user.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setDocument(user.getDocument());
        userResponse.setEmail(user.getEmail());
        userResponse.setUserType(user.getUserType());
        userResponse.setBalance(user.getBalance());
        userResponse.setCreatedAt(user.getCreatedAt());

        return userResponse;
    }
}
