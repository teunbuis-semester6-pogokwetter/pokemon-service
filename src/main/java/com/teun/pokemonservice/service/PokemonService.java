package com.teun.pokemonservice.service;

import com.teun.pokemonservice.models.Pokemon;
import com.teun.pokemonservice.repo.PokemonRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    @Autowired
    PokemonRepo repo;
    Logger logger = LoggerFactory.getLogger(PokemonService.class);
    @Cacheable(value = "pokemoncache", key = "#dexNumber")
    public Pokemon findPokemonByDexNumber(long dexNumber){
        Pokemon pokemon = findByPokemonDexNumberFromDatabase(dexNumber);
        return pokemon;
    }
    @Cacheable(value = "pokemoncache", key = "#name")
    public Pokemon findPokemonByName(String name){
        Pokemon found = findByNameFromDataBase(name);
        return found;
    }
    @Cacheable(value = "pokemoncache")
    public List<Pokemon> findAllPokemon(){
        List<Pokemon> found = findAllFromDataBase();
        return found;
    }
    private List<Pokemon> findAllFromDataBase(){
        logger.info("[🌟] Retreived pokemon from database [🌟]");
        return repo.findAll();
    }
    private Pokemon findByNameFromDataBase(String name){
        logger.info("[🌟] Retreived pokemon from database [🌟]");
        return repo.findByName(name).orElse(null);
    }
    private Pokemon findByPokemonDexNumberFromDatabase(long dexNumber){
        logger.info("[🌟] Retreived pokemon from database [🌟]");
        return repo.findByDexNumber(dexNumber).orElse(null);
    }
}
