package com.glm.mypet.controllers;

import com.glm.mypet.models.Owner;
import com.glm.mypet.services.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/owners", produces = MediaType.APPLICATION_JSON_VALUE)
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOwner(@Valid @RequestBody Owner owner) {
        // Verificar se o CPF, email ou celular já estão cadastrados
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
            // Salvar o novo proprietário
            Owner newOwner = ownerService.saveOwner(owner);
            return ResponseEntity.status(HttpStatus.CREATED).body("Proprietário criado com sucesso: ID " + newOwner.getId());
        } catch (Exception e) {
            // Tratar exceções e retornar uma resposta adequada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o proprietário: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable Long id) {
        // Buscar o proprietário pelo ID
        Optional<Owner> owner = ownerService.getOwnerById(id);
        return owner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable Long id) {
        // Verificar se o proprietário existe
        Optional<Owner> ownerOptional = ownerService.getOwnerById(id);
        if (ownerOptional.isEmpty()) { // Alteração para usar isEmpty() em vez de isPresent()
            return ResponseEntity.notFound().build();
        }

        // Verificar se o proprietário tem pets associados
        long petCount = ownerService.countPetsByOwnerId(id);
        if (petCount > 0) {
            // Retornar uma mensagem informando sobre a existência de pets
            return ResponseEntity.badRequest().body("Este proprietário tem pets associados. Não é possível excluir.");
        }

        // Excluir o proprietário
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Owner>> listAllOwners() {
        List<Owner> owners = ownerService.findAllOwners(); // Chamada ao serviço para obter todos os proprietários
        return ResponseEntity.ok(owners); // Retorna a lista de proprietários
    }

    @GetMapping("/search")
    public ResponseEntity<?> listOwners(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {

        // Buscar proprietários com base nos parâmetros fornecidos
        List<Owner> owners = ownerService.findOwners(id, name, email);

        // Verificar se nenhum proprietário foi encontrado
        if (owners.isEmpty()) {
            return ResponseEntity.status(404).body("Nenhum proprietário encontrado com as informações fornecidas.");
        }

        return ResponseEntity.ok(owners);
    }
}

