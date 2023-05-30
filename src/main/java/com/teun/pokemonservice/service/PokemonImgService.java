package com.teun.pokemonservice.service;

import com.teun.pokemonservice.models.PokemonImg;
import com.teun.pokemonservice.repo.PokemonImgRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class PokemonImgService {
    private static final String ERROR = "Error: ";
    @Autowired
    PokemonImgRepo repo;

    Logger logger = LoggerFactory.getLogger(PokemonImgService.class);

    public List<PokemonImg> findAllPokemonImgs() {
        return repo.findAll();
    }

    public PokemonImg findPokemonImgById(int id) {

        return repo.findById(id).orElse(null);
    }

    public PokemonImg findByPokemonId(int id) {

        return repo.findByPokemonid(id).orElse(null);
    }

    public int savePokemonImg(PokemonImg pokemonImg) {

        return repo.save(pokemonImg).getId();
    }
    public void savePokemonImgs(MultipartFile[] images){
        List<Map.Entry<String, byte[]>> mapped = mapImages(images);
        for ( Map.Entry<String, byte[]> image : mapped) {
            PokemonImg pokemonImg = new PokemonImg();
            int pokemonId = getPokemonIdFromFileName(image.getKey());
            pokemonImg.setPokemonid(pokemonId);
            pokemonImg.setPokemonpicture(image.getValue());
            repo.save(pokemonImg);
        }
    }
    private byte[] convertToByteArray(MultipartFile image) {
        byte[] imageToSave = new byte[0];
        try {
            imageToSave = image.getBytes();
        }
        catch (IOException e) {
            logger.error(ERROR + e);
        }
        return imageToSave;
    }

    private List<Map.Entry<String, byte[]>> mapImages(MultipartFile[] images) {
        Map<String, byte[]> mapped = new HashMap<>();
        try {
            Arrays.asList(images).stream().forEach(image -> {
                byte[] imageToSave = convertToByteArray(image);
                mapped.put(image.getOriginalFilename(), imageToSave);
            });
        }
        catch (Exception e) {
            logger.error(ERROR + e);
        }
        List<Map.Entry<String, byte[]>> list = new LinkedList<>(mapped.entrySet());
        return list;
    }
    private int getPokemonIdFromFileName(String fileName){
        int pokemonId;
        String clean = fileName.replaceAll(".png", "");
        StringBuilder sb = new StringBuilder(clean);
        while(sb.charAt(0) =='0'){
            sb.deleteCharAt(0);
        }
        try{
            pokemonId = Integer.parseInt(sb.toString());
        }
        catch (NumberFormatException e){
            logger.error(ERROR + e);
            pokemonId = 99999;
        }
        return pokemonId;
    }
}