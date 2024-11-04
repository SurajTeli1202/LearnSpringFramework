package com.rest.webservices.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HellWorldServiceApplication {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(HellWorldServiceApplication.class);

        HelloWorldController helloWorldController = ctx.getBean(HelloWorldController.class);
        System.out.println(helloWorldController.getCustomProperty());
        System.out.println(helloWorldController.getCustomProperty2());
    }
}
