package io.github.nivaldosilva.wallet.controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.github.nivaldosilva.wallet.dto.UserCreationDTO;
import io.github.nivaldosilva.wallet.dto.UserResponseDTO;
import io.github.nivaldosilva.wallet.entities.UserType;
import io.github.nivaldosilva.wallet.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreationDTO dto) {
        UserResponseDTO createdUser = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        UserResponseDTO user = userService.searchUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listUsers(@RequestParam(required = false) UserType userType) {
        if (userType != null) {
            List<UserResponseDTO> users = userService.listUsersByType(userType);
            return ResponseEntity.ok(users);
        }
        List<UserResponseDTO> users = userService.listAllUsers();
        return ResponseEntity.ok(users);
    }
}