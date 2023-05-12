package com.teun.pokemonservice.rabbitmq;

import com.teun.pokemonservice.service.UserPokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.concurrent.CountDownLatch;

@Component
    public class Receiver{
        private CountDownLatch latch = new CountDownLatch(1);

        public void receiveMessage(byte[] message){
            ByteArrayInputStream bis = new ByteArrayInputStream(message);
            ObjectInput in;
            Object userPokemon = null;
            try{
                in = new ObjectInputStream(bis);
                try{
                    userPokemon = in.readObject();
                }
                catch (Exception exception){
                    System.out.println("Error:" + exception);
                }
            }
            catch (IOException e){
                System.out.println("Error:" + e);
            }
            System.out.println("Recieved:"+ userPokemon );
            latch.countDown();
        }

        public CountDownLatch getLatch(){
            return latch;
        }
}
