package com.glm.mypet.repositories;

import com.glm.mypet.models.PetHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetHistoryRepository extends JpaRepository<PetHistory, Integer> { // Alterado para Integer
    List<PetHistory> findByPetId(Long petId);
}
