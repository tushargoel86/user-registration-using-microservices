package com.tushar.usermanagement.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class UserRegistrationConfig {

	@Value("${exchange-name}") 
	String exchangeName;
	
	@Value("${queue}") 
	String queue;
	
	@Value("${routingKey}") 
	String routingKey;
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchangeName);
	}
	
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}
	
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(routingKey); 
	}
	
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
    	ObjectMapper mapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(mapper);
    }
    
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
	}
	
//	   @Bean
//	    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//	            MessageListenerAdapter listenerAdapter) {
//	        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//	        container.setConnectionFactory(connectionFactory);
//	        container.setQueueNames(queue);
//	        container.setMessageListener(listenerAdapter);
//	        return container;
//	    }
//
//	    @Bean
//	    MessageListenerAdapter listenerAdapter(UserRegistrationNotification receiver) {
//	        return new MessageListenerAdapter(receiver, "receiveMessage");
//	    }
//	    
	public String getExchangeName() {
		return exchangeName;
	}
	
	public String getQueue() {
		return queue;
	}
	
	public String getRoutingKey() {
		return routingKey;
	}
}
