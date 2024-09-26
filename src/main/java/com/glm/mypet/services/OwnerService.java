package com.glm.mypet.services;

import com.glm.mypet.models.Owner;
import com.glm.mypet.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public Owner saveOwner(Owner owner) {
        try {
            return ownerRepository.save(owner);
        } catch (Exception e) {
            // Log do erro
            throw new RuntimeException("Erro ao salvar o proprietário: " + e.getMessage(), e);
        }
    }

    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }

    public boolean existsByCpf(String cpf) {
        return ownerRepository.existsByCpf(cpf);
    }

    public boolean existsByEmail(String email) {
        return ownerRepository.existsByEmail(email);
    }

    public boolean existsByCellphone(String cellphone) {
        return ownerRepository.existsByCellphone(cellphone);
    }

    public long countPetsByOwnerId(Long ownerId) {
        return ownerRepository.findById(ownerId)
            .map(owner -> owner.getPets().size())
            .orElse((int) 0L);
    }

    public List<Owner> findOwners(Long id, String name, String email) {
    // Implementar a lógica de busca no banco de dados, talvez usando o OwnerRepository
    return ownerRepository.findAll() // ou uma consulta personalizada
        .stream()
        .filter(owner -> (id == null || owner.getId().equals(id)) &&
                        (name == null || owner.getName().contains(name)) &&
                        (email == null || owner.getEmail().contains(email)))
        .collect(Collectors.toList());
}

    
    
}
