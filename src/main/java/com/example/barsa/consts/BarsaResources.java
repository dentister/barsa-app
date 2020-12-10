package com.example.barsa.consts;

public interface BarsaResources {
    String KAFKA_HOST = "localhost:9092";
    String USER_MANAGEMENT_KAFKA_TOPIC = "userManagementTopic";
    String TICKET_MANAGEMENT_KAFKA_TOPIC = "ticketManagementTopic";

    public interface BeanAliases {
        String TM_KAFKA_LISTENER_CONTAINER_FACTORY = "ticketManagementKafkaListenerContainerFactory";
        String TM_KAFKA_TEMPLATE = "ticketManagamentKafkaTemplate";
        String UM_KAFKA_TEMPLATE = "userManagamentKafkaTemplate";
    }
}
