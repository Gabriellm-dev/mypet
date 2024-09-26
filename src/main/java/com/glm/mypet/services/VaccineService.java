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

    public Vaccine save(Vaccine vaccine) {
        return repository.save(vaccine);
    }

    public List<Vaccine> findByPetId(Integer petId) {
        return repository.findByPetId(petId);
    }

    public List<Vaccine> findByNameAndPetId(String name, Integer petId) {
        return repository.findByNameAndPetId(name, petId);
    }
}
