package com.glm.mypet.controllers;

import com.glm.mypet.models.User;
import com.glm.mypet.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email já cadastrado.");
        }
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(201).body("Usuário criado com sucesso: ID " + savedUser.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Verifica se o email a ser atualizado já está em uso por outro usuário
        if (!existingUser.get().getEmail().equals(user.getEmail()) && userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email já cadastrado por outro usuário.");
        }

        user.setId(id); // Certifica-se de que o ID é o mesmo do usuário a ser atualizado
        userService.saveUser(user);
        return ResponseEntity.ok("Usuário atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userService.getUserById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
}
