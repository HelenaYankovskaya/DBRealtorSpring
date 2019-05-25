package com.it.app.config;


import org.springframework.context.annotation.*;

/**
 * General configuration class
 */
@Configuration
@ComponentScan(basePackages = "com.it")
@Import({WebConfiguration.class, DatabaseConfiguration.class, MessagesConfiguration.class, SecurityConfiguration.class})
public class AppConfiguration {

}
