package com.glm.mypet.controllers;

import com.glm.mypet.models.Vaccine;
import com.glm.mypet.services.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {

    @Autowired
    private VaccineService service;

    @PostMapping
    public ResponseEntity<Vaccine> create(@RequestBody Vaccine vaccine) {
        vaccine.setCreatedAt(LocalDateTime.now());
        Vaccine savedVaccine = service.save(vaccine);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVaccine);
    }

    @GetMapping
    public ResponseEntity<List<Vaccine>> list(
            @RequestParam(required = false) Integer petId,
            @RequestParam(required = false) String name) {
        
        if (petId != null && name != null) {
            List<Vaccine> vaccines = service.findByNameAndPetId(name, petId);
            return ResponseEntity.ok(vaccines);
        } else if (petId != null) {
            List<Vaccine> vaccines = service.findByPetId(petId);
            if (vaccines.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
            }
            return ResponseEntity.ok(vaccines);
        } else {
            return ResponseEntity.badRequest().build(); // Se nenhum parâmetro é passado
        }
    }
}
