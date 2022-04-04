package com.artem.subscriptionsmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SubscriptionsManagementSystemApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(SubscriptionsManagementSystemApplication.class, args);

        System.out.println(context.getBeanDefinitionCount());
    }

}
