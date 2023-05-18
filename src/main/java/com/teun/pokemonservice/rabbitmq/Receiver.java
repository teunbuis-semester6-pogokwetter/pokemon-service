package com.teun.pokemonservice.rabbitmq;

import com.teun.pokemonservice.dto.UserPokemonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
    public class Receiver{
        private CountDownLatch latch = new CountDownLatch(1);

        private Logger logger = LoggerFactory.getLogger(Receiver.class);

        public void receiveMessage(UserPokemonDTO userPokemonDTO){
            logger.info("[🌠] " + "Recieved from queue:"+ userPokemonDTO + " [🌠]");
            latch.countDown();
        }
        public CountDownLatch getLatch(){
            return latch;
        }
}
