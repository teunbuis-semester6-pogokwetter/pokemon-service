package com.teun.pokemonservice.service;

import com.teun.pokemonservice.model.Pokemon;
import com.teun.pokemonservice.repo.PokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    @Autowired
    PokemonRepo repo;

    public Pokemon findPokemonByDexNumber(long dexNumber){
        return repo.findByDexNumber(dexNumber).orElse(null);
    }
    public Pokemon findPokemonByName(String name){
        return repo.findByName(name).orElse(null);
    }
    public List<Pokemon> findAllPokemon(){
        return repo.findAll();
    }
}
