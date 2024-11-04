package com.rest.webservices;

import com.rest.webservices.helloworld.HelloWorldBean;
import com.rest.webservices.helloworld.HelloWorldController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulWebServicesApplication {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(RestfulWebServicesApplication.class);

        HelloWorldController helloWorldController = ctx.getBean(HelloWorldController.class);
        System.out.println(helloWorldController.getCustomProperty());
        System.out.println(helloWorldController.getCustomProperty2());
    }
}
