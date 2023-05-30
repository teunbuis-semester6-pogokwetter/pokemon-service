package com.teun.pokemonservice.rabbitmq;

import com.teun.pokemonservice.dto.UserPokemonDTO;
import com.teun.pokemonservice.models.UserPokemon;
import com.teun.pokemonservice.service.UserPokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
    public class Receiver{

    @Autowired
    UserPokemonService userPokemonService;
        private CountDownLatch latch = new CountDownLatch(1);

        private Logger logger = LoggerFactory.getLogger(Receiver.class);

        public void receiveMessage(UserPokemonDTO userPokemonDTO){
            logger.info("[ 🌠 ] " + "Recieved from queue:"+ userPokemonDTO + " [ 🌠 ]");
            userPokemonService.updateCache(userPokemonDTO.getUserId().toString());
            latch.countDown();
        }
        public CountDownLatch getLatch(){
            return latch;
        }
}
