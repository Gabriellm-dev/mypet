package com.glm.mypet.controllers;

import com.glm.mypet.models.PetHistory;
import com.glm.mypet.services.PetHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pet-history")
public class PetHistoryController {

    @Autowired
    private PetHistoryService service;

    @PostMapping
    public ResponseEntity<PetHistory> create(@RequestBody PetHistory history) {
        PetHistory savedHistory = service.save(history);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHistory);
    }

    @GetMapping
    public ResponseEntity<List<PetHistory>> list(@RequestParam(required = false) Long petId) {  
        List<PetHistory> histories;
        if (petId != null) {
            histories = service.findByPetId(petId);
            if (histories.isEmpty()) {
                // Retorne um ResponseEntity vazio ou um status 204 (No Content) ao invés de uma lista de strings
                return ResponseEntity.noContent().build(); 
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(histories);
    }
}

