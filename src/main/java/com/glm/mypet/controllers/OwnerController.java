package com.glm.mypet.controllers;

import com.glm.mypet.models.Owner;
import com.glm.mypet.services.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<String> deleteOwner(@PathVariable Long id) {
        Optional<Owner> ownerOptional = ownerService.getOwnerById(id);
        
        if (!ownerOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        long petCount = ownerService.countPetsByOwnerId(id);
        if (petCount > 0) {
            // Aqui você pode retornar uma mensagem informando sobre a existência de pets
            return ResponseEntity.badRequest().body("Este proprietário tem pets associados. Não é possível excluir.");
        }

        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }

        @GetMapping("/search")
        public ResponseEntity<?> listOwners(
                @RequestParam(required = false) Long id,
                @RequestParam(required = false) String name,
                @RequestParam(required = false) String email) {
            
            List<Owner> owners = ownerService.findOwners(id, name, email);
            
            if (owners.isEmpty()) {
                return ResponseEntity.status(404).body("Nenhum proprietário encontrado com as informações fornecidas.");
            }
            
            return ResponseEntity.ok(owners);
        }
    


}
