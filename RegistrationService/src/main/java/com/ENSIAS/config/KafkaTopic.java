package com.ENSIAS.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Value("${spring.kafka.topic1.name}")
    private String topic1;

    @Value("${spring.kafka.topic2.name}")
    private String topic2;

    @Bean
    public NewTopic registrationTopic(){
        return TopicBuilder.name(topic1).build();
    }

    @Bean
    public NewTopic postsTopic(){
        return TopicBuilder.name(topic2).build();
    }
}
