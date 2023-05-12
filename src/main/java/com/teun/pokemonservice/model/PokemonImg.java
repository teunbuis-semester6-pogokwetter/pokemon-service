package com.teun.pokemonservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pokemonimgs")
@NoArgsConstructor
@AllArgsConstructor
public class PokemonImg {

    //fields
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name= "pokemonid", nullable = false)
    private int pokemonid;

    @Lob
    @Column(name = "img", nullable = false, columnDefinition = "MEDIUMBLOB")
    private byte[] pokemonpicture;

    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPokemonid() {
        return pokemonid;
    }

    public void setPokemonid(int pokemonid) {
        this.pokemonid = pokemonid;
    }

    public byte[] getPokemonpicture() {
        return pokemonpicture;
    }

    public void setPokemonpicture(byte[] pokemonpicture) {
        this.pokemonpicture = pokemonpicture;
    }
}
