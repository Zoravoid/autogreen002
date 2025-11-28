package com.iucosoft.mylinksspringboot.config;

import com.iucosoft.mylinksspringboot.entities.Co2;
import com.iucosoft.mylinksspringboot.entities.Heat;
import com.iucosoft.mylinksspringboot.entities.Humidity;
import com.iucosoft.mylinksspringboot.entities.Moisture;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    //Consumer Configuration for the Moisture Sensor

    @Bean
    public ConsumerFactory<String, Moisture> moistureConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(),
                new StringDeserializer(),
                new JsonDeserializer<>(Moisture.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Moisture> moistureKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Moisture> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(moistureConsumerFactory());
        return factory;
    }

    //Consumer Configuration for the Heat Sensor

    @Bean
    public ConsumerFactory<String, Heat> heatConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(),
                new StringDeserializer(),
                new JsonDeserializer<>(Heat.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Heat> heatKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Heat> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(heatConsumerFactory());
        return factory;
    }

    //Consumer Configuration for the Humidity Sensor

    @Bean
    public ConsumerFactory<String, Humidity> humidityConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(),
        new StringDeserializer(),
        new JsonDeserializer<>(Humidity.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Humidity> humidityKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Humidity> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(humidityConsumerFactory());
        return factory;
    }

    //Consumer Configuration for the Co2 Sensor

    @Bean
    public ConsumerFactory<String, Co2> co2ConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(),
        new StringDeserializer(),
        new JsonDeserializer<>(Co2.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Co2> co2KafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Co2> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(co2ConsumerFactory());
        return factory;
    }

}
