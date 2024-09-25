package com.glm.mypet.models;

import jakarta.persistence.*;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 1)
    private String gender;

    @Column(nullable = false, length = 50)
    private String species;

    @Column(nullable = false, length = 50)
    private String breed;

    // Constructors, Getters, and Setters
    public Pet() {}

    public Pet(String name, Integer age, String gender, String species, String breed) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.species = species;
        this.breed = breed;
    }

    // Getters and Setters...
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
}

