package com.teun.pokemonservice.rabbitmq;

import com.teun.pokemonservice.models.UserPokemon;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Component
public class Publisher{
    private final Receiver receiver;
    private final RabbitTemplate rabbitTemplate;

    public Publisher(Receiver receiver, RabbitTemplate rabbitTemplate){
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishUserPokemon(UserPokemon userPokemon){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out;
        byte[] userPokemonBytes;
        try{
            out = new ObjectOutputStream(bos);
            out.writeObject(userPokemon);
            out.flush();
            userPokemonBytes = bos.toByteArray();
            rabbitTemplate.convertAndSend(MQConfig.EXCHANGENAME, MQConfig.ROUTINGKEY, userPokemonBytes);
            System.out.println("Send userPokemon to Queu ✨" + userPokemon);
//            try{
//                receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
//            }
//            catch (Exception exception){
//                System.out.println(exception);
//            }
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
