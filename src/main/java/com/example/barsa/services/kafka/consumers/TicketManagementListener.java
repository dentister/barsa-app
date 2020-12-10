package com.example.barsa.services.kafka.consumers;

import com.example.barsa.consts.BarsaResources;
import com.example.barsa.consts.BarsaResources.BeanAliases;
import com.example.barsa.processors.TicketManagementProcessor;
import com.example.barsa.xsd.ticket.input.DoTicketOperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class TicketManagementListener {
    @Autowired
    private TicketManagementProcessor processor;

    ExecutorService service = Executors.newFixedThreadPool(4);

    @KafkaListener(topics = BarsaResources.TICKET_MANAGEMENT_KAFKA_TOPIC, containerFactory = BeanAliases.TM_KAFKA_LISTENER_CONTAINER_FACTORY)
    public void handle(@Payload DoTicketOperation operation, @Headers MessageHeaders headers) {
        System.out.println("UserManagementListener.handle() started [operation=" + operation + "]");

        service.submit(() -> {
            try {
                processor.execute(operation);

                Thread.sleep(10000);

                System.out.println("UserManagementListener.executor finished [operation=" + operation + "]");
            } catch (Exception e) {
                e.printStackTrace();

                System.out.println("UserManagementListener.handle() finished with error");
            }
        });
    }
}
