package com.teun.pokemonservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
