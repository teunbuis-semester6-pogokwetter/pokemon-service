package com.teun.pokemonservice.service;

import com.teun.pokemonservice.dto.UserPokemonDTO;
import com.teun.pokemonservice.models.UserPokemon;
import com.teun.pokemonservice.repo.UserPokemonRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPokemonService {

    @Autowired
    UserPokemonRepo repo;

    @Autowired
    ModelMapper modelMapper;

    public List<UserPokemonDTO> findAllByUserId(Long userId){
        List<UserPokemon> found = repo.findByUserId(userId);
        return modelMapper.map(found, List.class);
    }
    public UserPokemonDTO saveUserPokemon(UserPokemonDTO userPokemonDTO){
        UserPokemon toSave = modelMapper.map(userPokemonDTO, UserPokemon.class);
        UserPokemon savedUserPokemon = repo.save(toSave);
        return modelMapper.map(savedUserPokemon, UserPokemonDTO.class);
    }
}
