package com.example.barsa.config;

import com.example.barsa.consts.BarsaResources;
import com.example.barsa.consts.BarsaResources.BeanAliases;
import com.example.barsa.xsd.ticket.input.DoTicketOperation;
import com.example.barsa.xsd.user.input.DoUserOperation;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfiguration {
    @Bean(name = "userManagamentKafkaTemplate")
    public KafkaTemplate<Long, DoUserOperation> userManagementKafkaTemplate() {
        DefaultKafkaProducerFactory<Long, DoUserOperation> producerFactory = new DefaultKafkaProducerFactory<>(
                producerConfigs(LongSerializer.class, JsonSerializer.class));

        return new KafkaTemplate<>(producerFactory);
    }

    @Bean(name = BeanAliases.TM_KAFKA_TEMPLATE)
    public KafkaTemplate<Long, DoTicketOperation> ticketManagementKafkaTemplate() {
        DefaultKafkaProducerFactory<Long, DoTicketOperation> producerFactory = new DefaultKafkaProducerFactory<>(
                producerConfigs(LongSerializer.class, JsonSerializer.class));

        return new KafkaTemplate<>(producerFactory);
    }

    private Map<String, Object> producerConfigs(Class<?> keySerializer, Class<?> valueSerializer) {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BarsaResources.KAFKA_HOST);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        props.put(ProducerConfig.ACKS_CONFIG, "1");

        return props;
    }
}
