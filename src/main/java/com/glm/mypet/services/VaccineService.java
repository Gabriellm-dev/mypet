package com.glm.mypet.services;

import com.glm.mypet.models.Vaccine;
import com.glm.mypet.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineService {

    @Autowired
    private VaccineRepository repository;

    // Salva uma vacina no repositório
    public Vaccine save(Vaccine vaccine) {
        return repository.save(vaccine);
    }

    // Busca vacinas por ID do pet
    public List<Vaccine> findByPetId(Integer petId) {
        return repository.findByPetId(petId);
    }

    // Busca vacinas por nome e ID do pet
    public List<Vaccine> findByNameAndPetId(String name, Integer petId) {
        return repository.findByNameAndPetId(name, petId);
    }

    // Busca todas as vacinas
    public List<Vaccine> findAll() {
        return repository.findAll(); // Chama o método findAll() do VaccineRepository
    }
}
