package com.teun.pokemonservice.controller;

import com.teun.pokemonservice.model.UserPokemon;
import com.teun.pokemonservice.rabbitmq.Publisher;
import com.teun.pokemonservice.service.UserPokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/userpokemon")
public class UserPokemonController {
    @Autowired
    UserPokemonService service;

    @Autowired
    Publisher publisher;
    @GetMapping("/userid/{id}")
    public ResponseEntity<List<UserPokemon>> getAllUserPokemon(@PathVariable(value = "id")Long userId){
        try {
            List<UserPokemon> userPokemons = service.findAllByUserId(userId);
            if(userPokemons != null && userPokemons.size() != 0){

                return ResponseEntity.ok().body(userPokemons);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping()
    public ResponseEntity<UserPokemon> createUserPokemon(@RequestBody UserPokemon userPokemon){
        try{
            UserPokemon savedUserPokemon = service.saveUserPokemon(userPokemon);
            publisher.publishUserPokemon(userPokemon);
            return ResponseEntity.ok().body(savedUserPokemon);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
