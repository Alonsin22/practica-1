package com.ejemplo.secureapi.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpsRedirectConfig {

    @Bean
    public TomcatServletWebServerFactory servletContainer(
            @Value("${server.http.port}") int httpPort,
            @Value("${server.port}") int httpsPort) {

        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL"); // obliga a usar HTTPS
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };

        Connector http = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        http.setScheme("http");
        http.setPort(httpPort);
        http.setSecure(false);
        http.setRedirectPort(httpsPort);
        tomcat.addAdditionalTomcatConnectors(http);
        return tomcat;
    }
}