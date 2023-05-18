package com.teun.pokemonservice.service;

import com.teun.pokemonservice.dto.UserPokemonDTO;
import com.teun.pokemonservice.models.UserPokemon;
import com.teun.pokemonservice.repo.UserPokemonRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPokemonService {

    @Autowired
    UserPokemonRepo repo;

    @Autowired
    ModelMapper modelMapper;
    Logger logger = LoggerFactory.getLogger(UserPokemonService.class);
    @Cacheable(value = "userpokemoncache", key = "#userId")
    public List<UserPokemonDTO> findAllByUserId(Long userId){
        List<UserPokemon> found = repo.findByUserId(userId);
        return modelMapper.map(found, List.class);
    }

    @Cacheable(value = "userpokemoncache")
    public List<UserPokemonDTO> findAll(){
        List<UserPokemonDTO> found = findAllFromDataBase();
        logger.info("found userpokemons");
        return found;
    }

    @CachePut(value = "userPokemon")
    public UserPokemonDTO saveUserPokemon(UserPokemonDTO userPokemonDTO){
        UserPokemon toSave = modelMapper.map(userPokemonDTO, UserPokemon.class);
        UserPokemon savedUserPokemon = repo.save(toSave);
        return modelMapper.map(savedUserPokemon, UserPokemonDTO.class);
    }
    private List<UserPokemonDTO>findAllFromDataBase(){
        List<UserPokemon> found = repo.findAll();
        logger.info("[🌟] Retreived userpokemon from database [🌟]");
        return modelMapper.map(found, List.class);
    }
}
