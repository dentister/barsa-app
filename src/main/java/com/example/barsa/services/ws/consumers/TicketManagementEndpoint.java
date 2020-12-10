package com.example.barsa.services.ws.consumers;

import com.example.barsa.consts.BarsaResources;
import com.example.barsa.consts.BarsaResources.BeanAliases;
import com.example.barsa.xsd.ticket.input.DoTicketOperation;

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
public class TicketManagementEndpoint {
    public static final String NAMESPACE_URI = "http://localhost:8899/Barsa/TM";
    public static final String LOCAL_PART = "doTicketOperation";

    @Autowired
    @Qualifier(value = BeanAliases.TM_KAFKA_TEMPLATE)
    private KafkaTemplate<Long, DoTicketOperation> kafkaTemplate;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = LOCAL_PART)
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void doTicketOperation(@RequestPayload DoTicketOperation request) {
        System.out.println("TicketManagementEndpoint.doTicketOperation() started: " + request);

        ListenableFuture<SendResult<Long,DoTicketOperation>> future = kafkaTemplate.send(BarsaResources.TICKET_MANAGEMENT_KAFKA_TOPIC, System.nanoTime(), request);

        future.addCallback(System.out::println, System.err::println);

        System.out.println("TicketManagementEndpoint.doTicketOperation() finished");
    }

}
