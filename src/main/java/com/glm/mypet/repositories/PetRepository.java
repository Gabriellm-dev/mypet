package com.glm.mypet.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.glm.mypet.models.Pet;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByNameAndOwnerName(String name, String ownerName);
}

