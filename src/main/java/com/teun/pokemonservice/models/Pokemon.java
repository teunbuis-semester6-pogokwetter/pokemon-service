package com.teun.pokemonservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "pokemons")
public class Pokemon {

    //fields
    private Long dexNumber;
    private String name;
    private Boolean legendary;
}
