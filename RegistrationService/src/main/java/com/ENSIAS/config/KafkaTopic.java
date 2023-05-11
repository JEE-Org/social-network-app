package com.ENSIAS.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    private String topic="registration";

    @Bean
    public NewTopic registrationTopic(){
        return TopicBuilder.name(topic).build();
    }
}
