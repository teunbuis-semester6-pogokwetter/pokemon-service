package com.teun.pokemonservice.repo;

import com.teun.pokemonservice.models.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PokemonRepo extends MongoRepository<Pokemon, String> {
    Optional<Pokemon> findByDexNumber(Long dexNumber);
    Optional<Pokemon> findByName(String name);
}
