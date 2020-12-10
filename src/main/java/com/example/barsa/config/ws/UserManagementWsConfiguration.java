package com.example.barsa.config.ws;

import com.example.barsa.services.ws.consumers.UserManagementEndpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.server.SmartEndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadRootSmartSoapEndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class UserManagementWsConfiguration extends AbstractBarsaWsConfiguration {
    @Bean(name = "UM")
    public SimpleWsdl11Definition simpleWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();

        wsdl11Definition.setWsdl(new ClassPathResource("wsdl/UserManagement.wsdl"));

        return wsdl11Definition;
    }

    @Bean(name = "umSmartEndpointInterceptor")
    public SmartEndpointInterceptor smartEndPointInterceptor() {
        PayloadValidatingInterceptor delegateInterceptor = new PayloadValidatingInterceptor();
        XsdSchema schema = new SimpleXsdSchema(new ClassPathResource("wsdl/UserManagement.xsd"));

        delegateInterceptor.setValidateRequest(true);
        delegateInterceptor.setValidateResponse(true);
        delegateInterceptor.setXsdSchema(schema);

        PayloadRootSmartSoapEndpointInterceptor endPointInterceptor = new PayloadRootSmartSoapEndpointInterceptor(
                delegateInterceptor, UserManagementEndpoint.NAMESPACE_URI, UserManagementEndpoint.LOCAL_PART);

        return endPointInterceptor;
    }
}
