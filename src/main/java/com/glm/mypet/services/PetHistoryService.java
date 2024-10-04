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
        // Aqui você pode adicionar validações adicionais se necessário
        if (history.getPet() == null) {
            throw new IllegalArgumentException("O histórico deve estar associado a um Pet.");
        }
        return repository.save(history);
    }

    public List<PetHistory> findByPetId(Long petId) {
        // Você pode querer adicionar uma verificação se petId não é nulo
        if (petId == null) {
            throw new IllegalArgumentException("O ID do Pet não pode ser nulo.");
        }
        return repository.findByPetId(petId);
    }
}
