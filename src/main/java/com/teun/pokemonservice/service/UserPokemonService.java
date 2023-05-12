package com.teun.pokemonservice.service;

import com.teun.pokemonservice.models.UserPokemon;
import com.teun.pokemonservice.repo.UserPokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPokemonService {

    @Autowired
    UserPokemonRepo repo;

    public List<UserPokemon> findAllByUserId(Long userId){
        List<UserPokemon> found = repo.findByUserId(userId);
        return found;
    }
    public UserPokemon saveUserPokemon(UserPokemon userPokemon){
        UserPokemon savedUserPokemon = repo.save(userPokemon);
        return savedUserPokemon;
    }
}
