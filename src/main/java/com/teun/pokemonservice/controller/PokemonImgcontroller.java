package com.teun.pokemonservice.controller;

import com.teun.pokemonservice.models.PokemonImg;
import com.teun.pokemonservice.service.PokemonImgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/pokemonimg")
public class PokemonImgcontroller {

    @Autowired
    PokemonImgService service;

    Logger logger = LoggerFactory.getLogger(PokemonImgcontroller.class);
    @GetMapping
    public ResponseEntity<List<PokemonImg>> getAllPkmnImg(){
        try{
            List<PokemonImg> pokemonImgs = service.findAllPokemonImgs();
            if(pokemonImgs != null){
                return ResponseEntity.ok().body(pokemonImgs);
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<byte[]> getPkmnImgById(@PathVariable(value = "id") int id){
        try{
            PokemonImg pokemonImg = service.findByPokemonId(id);
            if(pokemonImg != null){
                byte[] image = pokemonImg.getPokemonpicture();
                return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception e){
            logger.error("Error: " + e);
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/upload")
    public ResponseEntity<String> handleFilesUpload(@RequestParam("file") MultipartFile[] images){
        try{
            service.savePokemonImgs(images);
            return ResponseEntity.ok().body("Success");
        }
        catch (Exception e){
            logger.error("Error: " + e);
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

}
