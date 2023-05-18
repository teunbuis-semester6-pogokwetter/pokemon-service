package com.teun.pokemonservice.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.concurrent.CountDownLatch;

@Component
    public class Receiver{
        private CountDownLatch latch = new CountDownLatch(1);

        private Logger logger = LoggerFactory.getLogger(Receiver.class);

        public void receiveMessage(byte[] message){
            ByteArrayInputStream bis = new ByteArrayInputStream(message);
            ObjectInput in = null;
            Object userPokemon = null;
            try{
                in = new ObjectInputStream(bis);
            }
            catch (IOException e){
                logger.error("Error:" + e);
            }
            try{
                userPokemon = in.readObject();
            }
            catch (Exception exception){
                logger.error("Error:" + exception);
            }
            logger.info("Recieved:"+ userPokemon + " [🌠]");
            latch.countDown();
        }

        public CountDownLatch getLatch(){
            return latch;
        }
}
