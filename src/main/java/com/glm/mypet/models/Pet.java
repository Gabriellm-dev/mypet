package com.glm.mypet.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome do animal é obrigatório.")
    @Size(max = 150, message = "O nome do animal deve ter no máximo 150 caracteres.")
    private String name;

    @NotNull(message = "Espécie é obrigatória.")
    @Size(max = 50, message = "A espécie deve ter no máximo 50 caracteres.")
    private String species;

    @NotNull(message = "Raça é obrigatória.")
    @Size(max = 50, message = "A raça deve ter no máximo 50 caracteres.")
    private String breed;

    @NotNull(message = "Sexo é obrigatório.")
    @Size(min = 1, max = 1, message = "Sexo deve ser representado por 'M' ou 'F'.")
    @Pattern(regexp = "^[MF]$", message = "Sexo deve ser 'M' ou 'F'.")
    private String sex;

    @NotNull(message = "Idade é obrigatória.")
    @Min(value = 0, message = "A idade deve ser um valor positivo.")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @NotNull(message = "Proprietário é obrigatório.")
    @JsonBackReference
    private Owner owner;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Vaccine> vaccines = new ArrayList<>();

    public Pet() {}

    public Pet(String name, String species, String breed, String sex, Integer age, Owner owner) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.sex = sex;
        this.age = age;
        this.owner = owner;
    }

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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public void addVaccine(Vaccine vaccine) {
        vaccines.add(vaccine);
        vaccine.setPet(this); // Assume que Vaccine tem um método setPet()
    }

    public void removeVaccine(Vaccine vaccine) {
        vaccines.remove(vaccine);
        vaccine.setPet(null); // Assume que Vaccine tem um método setPet()
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) &&
               Objects.equals(name, pet.name) &&
               Objects.equals(species, pet.species) &&
               Objects.equals(breed, pet.breed) &&
               Objects.equals(sex, pet.sex) &&
               Objects.equals(age, pet.age) &&
               Objects.equals(owner, pet.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, species, breed, sex, age, owner);
    }
}
