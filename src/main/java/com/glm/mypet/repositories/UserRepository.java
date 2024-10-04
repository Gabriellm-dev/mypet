package com.glm.mypet.repositories;

import com.glm.mypet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface para operações de persistência de dados para a entidade User.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Verifica se um usuário com o email fornecido já existe.
     *
     * @param email o email a ser verificado
     * @return true se um usuário com o email já existir, false caso contrário
     */
    boolean existsByEmail(String email);
}
