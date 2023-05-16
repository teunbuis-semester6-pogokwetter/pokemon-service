package com.teun.pokemonservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDTO implements Serializable {
    private Long id;
    private Long dexNumber;
    private String name;
    private Boolean legendary;

}
