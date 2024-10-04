package com.glm.mypet.controllers;

import com.glm.mypet.models.Pet;
import com.glm.mypet.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<?> createPet(@Valid @RequestBody Pet pet, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errors.toString());
        }

        Pet newPet = petService.savePet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable Long id) {
        Pet pet = petService.getPetById(id);
        if (pet != null) {
            return ResponseEntity.ok(pet);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePet(@PathVariable Long id) {
        try {
            petService.deletePet(id);
            return ResponseEntity.ok("Pet excluído com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet não encontrado.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@PathVariable Long id, @Valid @RequestBody Pet pet, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(errors.toString());
        }

        try {
            Pet updatedPet = petService.updatePet(id, pet);
            return ResponseEntity.ok(updatedPet);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet não encontrado com o ID: " + id);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> listPets(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String ownerName) {

        List<Pet> pets = petService.findPets(id, name, ownerName);

        if (pets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não existem animais cadastrados com as informações fornecidas.");
        }

        return ResponseEntity.ok(pets);
    }
}
