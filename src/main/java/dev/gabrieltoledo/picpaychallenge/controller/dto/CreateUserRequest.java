package dev.gabrieltoledo.picpaychallenge.controller.dto;

import java.math.BigDecimal;

import dev.gabrieltoledo.picpaychallenge.domain.user.User;
import dev.gabrieltoledo.picpaychallenge.domain.user.UserType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateUserRequest {

    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 100, message = "First name must between 3 and 100 characters")
    private final String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 200, message = "Last name must be between 3 and 200 characters")
    private final String lastName;

    @NotBlank(message = "The email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be at most 100 characters")
    private final String email;

    @NotBlank(message = "The document is required")
    @Size(max = 25, message = "Document must be at most 25 characters")
    private final String document;

    @NotBlank(message = "The password is required")
    @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
    private final String password;

    @NotNull(message = "The balance is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be greater than or equal to 0.0")
    private final BigDecimal balance;

    @NotNull(message = "The user type is required")
    private final UserType userType;

    public User toDomain() {
        return new User(firstName, lastName, document, email, password, balance, userType);
    }
}