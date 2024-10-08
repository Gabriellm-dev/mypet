package com.glm.mypet.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 150)
    private String name;

    @NotNull
    @Size(min = 1, max = 1)
    @Pattern(regexp = "^[MF]$", message = "Gender must be 'M' or 'F'")
    private String gender;

    @NotNull
    @Size(max = 14)
    @Column(unique = true)
    private String cpf;

    @NotNull
    @Email
    @Size(max = 150)
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(max = 15)
    @Column(unique = true)
    private String cellphone;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Gerenciar a referência circular
    private List<Pet> pets = new ArrayList<>();

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    // Métodos para adicionar e remover pets
    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setOwner(this);
    }

    public void removePet(Pet pet) {
        pets.remove(pet);
        pet.setOwner(null);
    }
}
