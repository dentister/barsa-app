package com.example.barsa.services.ws;

import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadRootSmartSoapEndpointInterceptor;

@SuppressWarnings("rawtypes")
public class BarsaEndpointInterceptor extends PayloadRootSmartSoapEndpointInterceptor {
    private final Class endpointClass;
    public BarsaEndpointInterceptor(Class endpointClass, EndpointInterceptor delegate, String namespaceUri, String localPart) {
        super(delegate, namespaceUri, localPart);

        this.endpointClass = endpointClass;
    }

    @Override
    public boolean shouldIntercept(MessageContext messageContext, Object endpoint) {
        System.out.println("shouldIntercept: messageContext=" + messageContext + ", endpoint=" + endpoint + ", class=" + endpointClass);

        return endpointClass.isInstance(endpoint);
    }

}
