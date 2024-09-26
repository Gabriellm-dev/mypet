package com.glm.mypet.services;

import com.glm.mypet.models.Pet;
import com.glm.mypet.repositories.PetRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    public Pet updatePet(Long id, Pet updatedPet) {
        if (!petRepository.existsById(id)) {
            throw new RuntimeException("Pet não encontrado com ID: " + id);
        }
        updatedPet.setId(id);
        return petRepository.save(updatedPet);
    }

    public List<Pet> findPets(Long id, String name, String ownerName) {
        if (id != null) {
            return petRepository.findById(id).map(List::of).orElse(List.of());
        }
        return petRepository.findByNameAndOwnerName(name, ownerName);
    }
    
}

