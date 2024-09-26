package com.glm.mypet.controllers;

import com.glm.mypet.models.Owner;
import com.glm.mypet.services.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public ResponseEntity<String> createOwner(@Valid @RequestBody Owner owner) {
        if (ownerService.existsByCpf(owner.getCpf())) {
            return ResponseEntity.badRequest().body("CPF já cadastrado.");
        }
        if (ownerService.existsByEmail(owner.getEmail())) {
            return ResponseEntity.badRequest().body("Email já cadastrado.");
        }
        if (ownerService.existsByCellphone(owner.getCellphone())) {
            return ResponseEntity.badRequest().body("Celular já cadastrado.");
        }

        try {
            Owner newOwner = ownerService.saveOwner(owner);
            return ResponseEntity.status(201).body("Proprietário criado com sucesso: ID " + newOwner.getId());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar o proprietário: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable Long id) {
        Optional<Owner> owner = ownerService.getOwnerById(id);
        return owner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        if (!ownerService.getOwnerById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
}
