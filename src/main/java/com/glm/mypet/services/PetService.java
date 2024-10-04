package com.glm.mypet.services;

import com.glm.mypet.models.Pet;
import com.glm.mypet.models.Owner; // Importar a classe Owner
import com.glm.mypet.repositories.PetRepository;
import com.glm.mypet.repositories.OwnerRepository; // Importar a interface OwnerRepository

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private OwnerRepository ownerRepository; // Injetar o repositório de Owner

    public Pet savePet(Pet pet) {
        // Verificar se o Owner está presente e persistido
        if (pet.getOwner() == null || pet.getOwner().getId() == null) {
            throw new IllegalArgumentException("O proprietário deve ser fornecido e estar persistido.");
        }

        // Buscar o Owner persistido
        Owner owner = ownerRepository.findById(pet.getOwner().getId())
            .orElseThrow(() -> new IllegalArgumentException("Proprietário não encontrado no banco de dados."));
        
        pet.setOwner(owner); // Associar o Owner ao Pet

        return petRepository.save(pet);
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    public void deletePet(Long id) {
        if (!petRepository.existsById(id)) {
            throw new RuntimeException("Pet não encontrado com ID: " + id);
        }
        petRepository.deleteById(id);
    }

    public Pet updatePet(Long id, Pet updatedPet) {
        if (!petRepository.existsById(id)) {
            throw new RuntimeException("Pet não encontrado com ID: " + id);
        }

        // Verificar se o Owner está presente e persistido
        if (updatedPet.getOwner() == null || updatedPet.getOwner().getId() == null) {
            throw new IllegalArgumentException("O proprietário deve ser fornecido e estar persistido.");
        }

        // Buscar o Owner persistido
        Owner owner = ownerRepository.findById(updatedPet.getOwner().getId())
            .orElseThrow(() -> new IllegalArgumentException("Proprietário não encontrado no banco de dados."));

        updatedPet.setOwner(owner); // Associar o Owner ao Pet

        updatedPet.setId(id);
        return petRepository.save(updatedPet);
    }

    public List<Pet> findPets(Long id, String name, String ownerName) {
        if (id != null) {
            return petRepository.findById(id).map(List::of).orElse(List.of());
        }
        if (name != null && ownerName != null) {
            return petRepository.findByNameAndOwner_Name(name, ownerName); // Usar a notação correta para a propriedade Owner
        } else if (name != null) {
            return petRepository.findByName(name);
        } else if (ownerName != null) {
            return petRepository.findByOwner_Name(ownerName); // Usar a notação correta para a propriedade Owner
        }

        return petRepository.findAll();
    }
}
