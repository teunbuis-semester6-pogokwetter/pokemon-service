package com.teun.pokemonservice.Service;

import com.teun.pokemonservice.Model.Pokemon;
import com.teun.pokemonservice.Repo.PokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    @Autowired
    PokemonRepo repo;

    public Pokemon findPokemonById(int id){
        return repo.findById(id).orElse(null);
    }
    public Pokemon findPokemonByName(String name){
        return repo.findByName(name).orElse(null);
    }
    public List<Pokemon> findAllPokemon(){
        return repo.findAll();
    }
}
