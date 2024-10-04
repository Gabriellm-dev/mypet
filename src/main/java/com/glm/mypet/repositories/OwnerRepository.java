package com.glm.mypet.repositories;

import com.glm.mypet.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Adicionando a anotação @Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    // Verifica se um proprietário existe pelo CPF
    boolean existsByCpf(String cpf);
    
    // Verifica se um proprietário existe pelo email
    boolean existsByEmail(String email);
    
    // Verifica se um proprietário existe pelo celular
    boolean existsByCellphone(String cellphone);

    // Busca todos os proprietários
    @Override
    List<Owner> findAll();
}
