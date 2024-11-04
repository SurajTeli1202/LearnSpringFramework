package com.springcore.enterprise.example.web;

import com.springcore.enterprise.example.business.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MyWebController {

    @Autowired
    BusinessService businessService;
    public long returnValueFromBusinessService(){
        return businessService.calculateSum();
    }
}

