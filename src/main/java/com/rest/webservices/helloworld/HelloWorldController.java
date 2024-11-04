package com.rest.webservices.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public String getCustomProperty() {
        return customProperty;
    }

    public void setCustomProperty(String customProperty) {
        this.customProperty = customProperty;
    }

    public String getCustomProperty2() {
        return customProperty2;
    }

    public void setCustomProperty2(String customProperty2) {
        this.customProperty2 = customProperty2;
    }

    @Value("${my.custom.property}")
    private String customProperty;

    @Value("${my.custom.property2}")
    private String customProperty2;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    //    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping( path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping( path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello Suraj");
    }

    @GetMapping( path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldBeanByName(@PathVariable String name){
        return new HelloWorldBean("Hello "+ name);
    }

    @GetMapping( path = "/hello-world-Internationalized")
    public String helloWorldInternationalized(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null, "Default Message", locale);
    }

}
