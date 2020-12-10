package com.example.barsa.config.ws;

import com.example.barsa.services.ws.consumers.TicketManagementEndpoint;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.SmartEndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadRootSmartSoapEndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class TicketManagementWsConfiguration extends AbstractBarsaWsConfiguration {
    @Bean(name = "TM")
    public SimpleWsdl11Definition simpleWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();

        wsdl11Definition.setWsdl(new ClassPathResource("wsdl/TicketManagement.wsdl"));

        return wsdl11Definition;
    }

    @Bean(name = "tmSmartEndpointInterceptor")
    public SmartEndpointInterceptor smartEndPointInterceptor(List<EndpointInterceptor> interceptors) {
        PayloadValidatingInterceptor delegateInterceptor = new PayloadValidatingInterceptor();
        XsdSchema schema = new SimpleXsdSchema(new ClassPathResource("wsdl/TicketManagement.xsd"));

        delegateInterceptor.setValidateRequest(true);
        delegateInterceptor.setValidateResponse(true);
        delegateInterceptor.setXsdSchema(schema);

        PayloadRootSmartSoapEndpointInterceptor endPointInterceptor = new PayloadRootSmartSoapEndpointInterceptor(
                delegateInterceptor, TicketManagementEndpoint.NAMESPACE_URI, TicketManagementEndpoint.LOCAL_PART);

        return endPointInterceptor;
    }
}
