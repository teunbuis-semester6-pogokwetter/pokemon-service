package com.teun.pokemonservice.Repo;

import com.teun.pokemonservice.Model.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PokemonRepo extends MongoRepository<Pokemon, String> {
    Optional<Pokemon> findByDexNumber(Long dexNumber);
    Optional<Pokemon> findByName(String name);
}
