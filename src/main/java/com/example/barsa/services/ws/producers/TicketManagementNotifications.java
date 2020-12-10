package com.example.barsa.services.ws.producers;

import com.example.barsa.xsd.ticket.output.Notification;

import javax.annotation.PostConstruct;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Component
public class TicketManagementNotifications extends WebServiceGatewaySupport {
    @PostConstruct
    public void init() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setContextPath("com.example.barsa.xsd.ticket.output");

        setDefaultUri("http://localhost:8899/Barsa/notifyTM");
        setMarshaller(marshaller);
        setUnmarshaller(marshaller);
    }

    public void sendNotification(Notification notification) {
        System.out.println("UserManagementNotifications.sendNotification() started. Package=" + Notification.class.getPackageName());

        Object response = getWebServiceTemplate().marshalSendAndReceive("http://localhost:8088/notifyTM", notification);

        System.out.println("UserManagementNotifications.sendNotification() finished [response=" + response +"]");
    }
}
