package com.glm.mypet.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.glm.mypet.models.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}

