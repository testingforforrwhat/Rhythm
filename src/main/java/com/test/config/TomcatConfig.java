package com.test.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

  @Bean
  public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
    return (factory) -> {
      factory.setPort(8001);
      factory.setContextPath("");
      factory.addConnectorCustomizers((connector) -> {
        connector.setProperty("maxThreads", "200");
        connector.setProperty("minSpareThreads", "10");
        connector.setProperty("acceptCount", "100");
      });
    };
  }
}