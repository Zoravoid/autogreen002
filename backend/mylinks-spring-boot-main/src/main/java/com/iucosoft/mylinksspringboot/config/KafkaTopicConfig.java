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
    public NewTopic bLightTopic() { return TopicBuilder.name("b_light").build(); }

    @Bean
    public NewTopic sMoistureTopic() { return TopicBuilder.name("s_moisture").build(); }

    @Bean
    public NewTopic sHeatTopic() { return TopicBuilder.name("s_heat").build(); }

    @Bean
    public NewTopic sHumidityTopic() { return TopicBuilder.name("s_humidity").build(); }

    @Bean
    public NewTopic sCo2Topic() { return TopicBuilder.name("s_co2").build(); }

    @Bean
    public NewTopic sLightTopic() { return TopicBuilder.name("s_light").build(); }

    @Bean
    public NewTopic cStreamTopic() { return TopicBuilder.name("c_stream").build(); }

    @Bean
    public NewTopic cStream2Topic() { return TopicBuilder.name("c_stream_2").build(); }

    @Bean
    public NewTopic cdetectionTopic() { return TopicBuilder.name("c_detection").build(); }

    @Bean
    public NewTopic cdetection2Topic() { return TopicBuilder.name("c_detection_2").build(); }

    @Bean
    public NewTopic sPlayerData() { return  TopicBuilder.name("s_player_data").build(); }

    @Bean
    public NewTopic bPlayerData() { return TopicBuilder.name("b_player_data").build(); }

}