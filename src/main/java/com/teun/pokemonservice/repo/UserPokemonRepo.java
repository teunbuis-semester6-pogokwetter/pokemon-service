package com.teun.pokemonservice.repo;

import com.teun.pokemonservice.models.UserPokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPokemonRepo extends MongoRepository<UserPokemon, String> {
    List<UserPokemon> findByUserId(Long userId);
    List<UserPokemon> findByNickName(String nickName);
}
