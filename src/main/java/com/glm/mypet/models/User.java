package com.glm.mypet.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome é obrigatório.")
    @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres.")
    private String name;

    @NotNull(message = "Gênero é obrigatório.")
    @Size(min = 1, max = 1, message = "Gênero deve ser representado por uma letra (M ou F).")
    private String gender;

    @NotNull(message = "E-mail é obrigatório.")
    @Email(message = "E-mail deve ser válido.")
    @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres.")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Senha é obrigatória.")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
    private String password;

    @NotNull(message = "Grupo é obrigatório.")
    @Size(max = 10, message = "O grupo deve ter no máximo 10 caracteres.")
    @Column(name = "user_group")
    private String group;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
