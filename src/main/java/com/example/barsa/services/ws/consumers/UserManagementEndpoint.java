package com.example.barsa.services.ws.consumers;

import com.example.barsa.consts.BarsaResources;
import com.example.barsa.consts.BarsaResources.BeanAliases;
import com.example.barsa.xsd.user.input.DoUserOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Endpoint
public class UserManagementEndpoint {
    public static final String NAMESPACE_URI = "http://localhost:8899/Barsa/UM";
    public static final String LOCAL_PART = "doUserOperation";

    @Autowired
    @Qualifier(value = BeanAliases.UM_KAFKA_TEMPLATE)
    private KafkaTemplate<Long, DoUserOperation> kafkaTemplate;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = LOCAL_PART)
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void doUserOperation(@RequestPayload DoUserOperation request) {
        System.out.println("UserManagementEndpoint.doUserOperation() started");

        ListenableFuture<SendResult<Long,DoUserOperation>> future = kafkaTemplate.send(BarsaResources.USER_MANAGEMENT_KAFKA_TOPIC, System.nanoTime(), request);

        future.addCallback(System.out::println, System.err::println);

        System.out.println("UserManagementEndpoint.doUserOperation() finished");
    }

}
