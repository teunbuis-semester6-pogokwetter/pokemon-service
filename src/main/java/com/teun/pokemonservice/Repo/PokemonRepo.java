package com.teun.pokemonservice.Repo;

import com.teun.pokemonservice.Model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PokemonRepo extends JpaRepository<Pokemon, Integer> {
    Optional<Pokemon> findById(int id);
    Optional<Pokemon> findByName(String name);
}
