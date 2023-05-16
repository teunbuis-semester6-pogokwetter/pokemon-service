package com.teun.pokemonservice.rabbitmq;

import com.teun.pokemonservice.dto.UserPokemonDTO;
import com.teun.pokemonservice.models.UserPokemon;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Component
public class Publisher{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishUserPokemon(UserPokemonDTO userPokemonDTO){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out;
        byte[] userPokemonBytes = null;
        try{
            out = new ObjectOutputStream(bos);
            out.writeObject(userPokemonDTO);
            out.flush();
            userPokemonBytes = bos.toByteArray();
        }
        catch (IOException ioException){
            System.out.println("Error:" + ioException);
        }
        try{
            if(userPokemonBytes != null){
                rabbitTemplate.convertAndSend(MQConfig.EXCHANGENAME, MQConfig.ROUTINGKEY, userPokemonBytes);
                System.out.println("Send userPokemon to Queue ✨" + userPokemonDTO);
            }
            else{
                throw new Exception("Something went wrong with reading the object");
            }
        }catch (Exception e){
            System.out.println("Error:" + e);
        }
    }
}
