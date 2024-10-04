package com.glm.mypet.services;

import com.glm.mypet.models.User;
import com.glm.mypet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Salva um novo usuário ou atualiza um existente.
     *
     * @param user o usuário a ser salvo
     * @return o usuário salvo
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Recupera um usuário pelo ID.
     *
     * @param id o ID do usuário
     * @return um Optional contendo o usuário, se encontrado
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Exclui um usuário pelo ID.
     *
     * @param id o ID do usuário a ser excluído
     */
    public void deleteUser(Long id) {
        // Verifica se o usuário existe antes de tentar excluir
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        userRepository.deleteById(id);
    }

    /**
     * Verifica se um usuário com o email fornecido já existe.
     *
     * @param email o email a ser verificado
     * @return true se o email já estiver cadastrado, false caso contrário
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Recupera todos os usuários.
     *
     * @return uma lista de usuários
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
