package com.iucosoft.mylinksspringboot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic bMoistureTopic() {
        return TopicBuilder.name("b_moisture").build();
    }

    @Bean
    public NewTopic bHeatTopic() { return TopicBuilder.name("b_heat").build(); }

    @Bean
    public NewTopic bHumidityTopic() { return TopicBuilder.name("b_humidity").build(); }

    @Bean
    public NewTopic bCo2Topic() { return TopicBuilder.name("b_co2").build(); }

    @Bean
    public NewTopic sMoistureTopic() { return TopicBuilder.name("s_moisture").build(); }

    @Bean
    public NewTopic sHeatTopic() { return TopicBuilder.name("s_heat").build(); }

    @Bean
    public NewTopic sHumidityTopic() { return TopicBuilder.name("s_humidity").build(); }

    @Bean
    public NewTopic sCo2Topic() { return TopicBuilder.name("s_co2").build(); }

    @Bean
    public NewTopic cImageTopic() { return TopicBuilder.name("c_image").build(); }

    @Bean
    public NewTopic cStreamTopic() { return TopicBuilder.name("c_stream").build(); }

}