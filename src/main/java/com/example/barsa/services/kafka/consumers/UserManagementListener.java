package com.example.barsa.services.kafka.consumers;

import com.example.barsa.consts.BarsaResources;
import com.example.barsa.processors.UserManagementProcessor;
import com.example.barsa.xsd.user.input.DoUserOperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class UserManagementListener {
    @Autowired
    private UserManagementProcessor processor;

    ExecutorService service = Executors.newFixedThreadPool(4);

    @KafkaListener(topics = BarsaResources.USER_MANAGEMENT_KAFKA_TOPIC, containerFactory = "userManagementKafkaListenerContainerFactory")
    public void handle(@Payload DoUserOperation operation, @Headers MessageHeaders headers) {
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
