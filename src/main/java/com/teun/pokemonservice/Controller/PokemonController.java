package com.teun.pokemonservice.Controller;

import com.teun.pokemonservice.Model.Pokemon;
import com.teun.pokemonservice.Service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    @Autowired
    PokemonService service;

    @GetMapping("/dex/{dexNumber}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable(value = "dexNumber")Long dexNumber){
        try{
            Pokemon pokemon = service.findPokemonByDexNumber(dexNumber);
            if(pokemon != null){
                return ResponseEntity.ok().body(pokemon);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping()
    public ResponseEntity<List<Pokemon>> getAllPokemon(){
        try{
            List<Pokemon> pokemons = service.findAllPokemon();
            if(pokemons != null){
                return ResponseEntity.ok().body(pokemons);

            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
