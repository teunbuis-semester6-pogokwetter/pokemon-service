package com.teun.pokemonservice.service;

import com.teun.pokemonservice.dto.UserPokemonDTO;
import com.teun.pokemonservice.models.UserPokemon;
import com.teun.pokemonservice.repo.UserPokemonRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPokemonService {

    private static final String FROM_DATABASE = "[ 🌟 ] Retreived pokemon from database [ 🌟 ]";
    @Autowired
    UserPokemonRepo repo;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CacheManager cacheManager;


    Logger logger = LoggerFactory.getLogger(UserPokemonService.class);
    @Cacheable(value = "userpokemoncache", key = "#userId")
    public List<UserPokemonDTO> findAllByUserId(Long userId){
        return findAllByUserIdFromDatabase(userId);
    }

    @Cacheable(value = "userpokemoncache")
    public List<UserPokemonDTO> findAll(){
        logger.info("found userpokemons");
        return findAllFromDataBase();
    }

    @CachePut(value = "userPokemon", key ="#userId")
    public UserPokemonDTO saveUserPokemon(UserPokemonDTO userPokemonDTO, long userId){
        return saveUserPokemonToDatabase(userPokemonDTO);
    }

    private List<UserPokemonDTO> findAllByUserIdFromDatabase(long userId){
        List<UserPokemon> found = repo.findByUserId(userId);
        logger.info(FROM_DATABASE);
        return modelMapper.map(found, List.class);
    }

    private UserPokemonDTO saveUserPokemonToDatabase(UserPokemonDTO userPokemonDTO){
        UserPokemon toSave = modelMapper.map(userPokemonDTO, UserPokemon.class);
        UserPokemon savedUserPokemon = repo.save(toSave);
        logger.info("[ 🌟 ] Saved userpokemon to database [ 🌟 ]");
        return modelMapper.map(savedUserPokemon, UserPokemonDTO.class);
    }
    private List<UserPokemonDTO>findAllFromDataBase(){
        List<UserPokemon> found = repo.findAll();
        logger.info(FROM_DATABASE);
        return modelMapper.map(found, List.class);
    }

    public void updateCache(String cacheKey){
        Cache cache = cacheManager.getCache("userpokemoncache");
        if(cache != null){
            cache.clear();
            cache.evict(cacheKey);
            logger.info("[ 🧹 ] Cache Cleared for key: " + cacheKey + " [ 🧹 ]");
        }
    }
}
