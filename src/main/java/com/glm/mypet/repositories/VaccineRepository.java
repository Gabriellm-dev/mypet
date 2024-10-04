package com.glm.mypet.repositories;

import com.glm.mypet.models.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {
    List<Vaccine> findByPetId(Integer petId); // Busca vacinas por ID do pet
    List<Vaccine> findByNameAndPetId(String name, Integer petId); // Busca vacinas por nome e ID do pet
}
