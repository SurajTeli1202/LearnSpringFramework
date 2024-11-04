package com.springboot.learnspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrenyConfigurationController {

    @Autowired
    private CurrenyServiceConfiguration configuration;

    @RequestMapping("/curreny-configuration")
    public CurrenyServiceConfiguration retrieveAllCourses(){
        return configuration;
    }

}
