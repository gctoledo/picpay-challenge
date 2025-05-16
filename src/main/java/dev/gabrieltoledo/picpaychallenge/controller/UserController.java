package dev.gabrieltoledo.picpaychallenge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gabrieltoledo.picpaychallenge.controller.dto.CreateUserRequest;
import dev.gabrieltoledo.picpaychallenge.controller.dto.UserResponse;
import dev.gabrieltoledo.picpaychallenge.domain.user.User;
import dev.gabrieltoledo.picpaychallenge.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserRequest user) {
        UserResponse createdUser = userService.createUser(user);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.findAll();

        List<UserResponse> usersResponse = users.stream()
                .map(UserResponse::build)
                .toList();

        return new ResponseEntity<>(usersResponse, HttpStatus.OK);
    }
}
