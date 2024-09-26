package com.glm.mypet.repositories;

import com.glm.mypet.models.PetHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetHistoryRepository extends JpaRepository<PetHistory, Integer> {
    List<PetHistory> findByPetId(Integer petId);
}
