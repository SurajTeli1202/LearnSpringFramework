package com.learnJPAandHibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( basePackages = {"com.*"} )
public class LearnJPAandHibernate {

    public static void main(String[] args) {
        SpringApplication.run(LearnJPAandHibernate.class);
    }
}
