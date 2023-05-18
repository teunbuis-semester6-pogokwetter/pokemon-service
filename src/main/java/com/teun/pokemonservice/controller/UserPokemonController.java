package com.teun.pokemonservice.controller;

import com.teun.pokemonservice.dto.PokemonDTO;
import com.teun.pokemonservice.dto.UserPokemonDTO;
import com.teun.pokemonservice.models.Pokemon;
import com.teun.pokemonservice.models.UserPokemon;
import com.teun.pokemonservice.rabbitmq.Publisher;
import com.teun.pokemonservice.service.UserPokemonService;
import org.modelmapper.ModelMapper;
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
    public ResponseEntity<List<UserPokemonDTO>> getAllUserPokemon(@PathVariable(value = "id")Long userId){
        try {
            List<UserPokemonDTO> userPokemonDTOs = service.findAllByUserId(userId);
            if(userPokemonDTOs != null && userPokemonDTOs.isEmpty() == false){

                return ResponseEntity.ok().body(userPokemonDTOs);
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
    public ResponseEntity<UserPokemonDTO> createUserPokemon(@RequestBody UserPokemonDTO userPokemonDTO){
        try{
            UserPokemonDTO savedUserPokemonDTO = service.saveUserPokemon(userPokemonDTO);
            publisher.publishUserPokemon(savedUserPokemonDTO);
            return ResponseEntity.ok().body(savedUserPokemonDTO);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
