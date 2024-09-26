package com.glm.mypet.services;

import com.glm.mypet.models.PetHistory;
import com.glm.mypet.repositories.PetHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetHistoryService {

    @Autowired
    private PetHistoryRepository repository;

    public PetHistory save(PetHistory history) {
        return repository.save(history);
    }

    public List<PetHistory> findByPetId(Integer petId) {
        return repository.findByPetId(petId);
    }
}
