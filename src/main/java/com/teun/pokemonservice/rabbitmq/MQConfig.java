package com.teun.pokemonservice.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class MQConfig {
    static final String QUEUENAME = "userpokemon_queue";
    static final String EXCHANGENAME = "userpokemon_exchange";
    static final String ROUTINGKEY = "userpokemon_routingkey";

    @Bean
    Queue userPokemonQueue(){
        return new Queue(QUEUENAME, true);
    }

    @Bean
    TopicExchange userPokemonExchange(){
        return new TopicExchange(EXCHANGENAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUENAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

}
