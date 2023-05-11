package com.teun.pokemonservice.Service;

import com.teun.pokemonservice.Model.UserPokemon;
import com.teun.pokemonservice.Repo.UserPokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPokemonService {

    @Autowired
    UserPokemonRepo repo;

    public List<UserPokemon> findAllByUserId(Long userId){
        return repo.findByUserId(userId);
    }
    public UserPokemon saveUserPokemon(UserPokemon userPokemon){
        UserPokemon savedUserPokemon = repo.save(userPokemon);
        return savedUserPokemon;
    }
}
