package com.teun.pokemonservice.rabbitmq;

import com.teun.pokemonservice.dto.UserPokemonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Publisher{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    Logger logger = LoggerFactory.getLogger(Publisher.class);

    public void publishUserPokemonDTO (UserPokemonDTO userPokemonDTO){
        try{
            rabbitTemplate.convertAndSend(MQConfig.EXCHANGENAME, MQConfig.ROUTINGKEY, userPokemonDTO);
            logger.info("[✨] " + "Send userPokemon to Queue: " + userPokemonDTO + " [✨]");
        }
        catch (Exception e){
            logger.error("Error:" + e);
        }
    }
}
