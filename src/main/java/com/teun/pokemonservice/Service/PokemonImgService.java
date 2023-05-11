package com.teun.pokemonservice.Service;

import com.teun.pokemonservice.Model.PokemonImg;
import com.teun.pokemonservice.Repo.PokemonImgRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class PokemonImgService {

    @Autowired
    PokemonImgRepo repo;

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
        } catch (IOException e) {
            throw new RuntimeException(e);
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

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<Map.Entry<String, byte[]>> list = new LinkedList<>(mapped.entrySet());
        return list;
    }
    private int getPokemonIdFromFileName(String fileName){
        int pokemonId;
        fileName.replaceAll(".png", "");
        StringBuilder sb = new StringBuilder(fileName);
        while(sb.charAt(0) =='0'){
            sb.deleteCharAt(0);
        }
        try{
            pokemonId = Integer.parseInt(sb.toString());

        }
        catch (NumberFormatException e){
            pokemonId = 99999;
        }
        return pokemonId;
    }
}