package com.glm.mypet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.glm.mypet.models.Pet;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    // Encontrar pets pelo nome e pelo nome do proprietário
    List<Pet> findByNameAndOwner_Name(String name, String ownerName);
    
    // Encontrar pets apenas pelo nome
    List<Pet> findByName(String name);
    
    // Encontrar pets pelo nome do proprietário
    List<Pet> findByOwner_Name(String ownerName);
}
