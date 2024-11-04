package com.rest.webservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/persons")
    public PersonV1 getFirstVersionPerson() {
        return new PersonV1("Suraj Teli");
    }

    @GetMapping("/v2/persons")
    public PersonV2 getSecondVersionPerson() {
        return new PersonV2(new Name("Suraj", "Teli"));
    }

    @GetMapping(path = "/persons", params = "version=1")
    public PersonV1 getFirstVersionPersonByRequestParam() {
        return new PersonV1("Suraj Teli");
    }

    @GetMapping(path = "/persons", params = "version=2")
    public PersonV2 getSecondVersionPersonByRequestParam() {
        return new PersonV2(new Name("Suraj", "Teli"));
    }

    @GetMapping(path = "/persons", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionPersonByRequestHeader() {
        return new PersonV1("Suraj Teli");
    }

    @GetMapping(path = "/persons", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionPersonByRequestHeader() {
        return new PersonV2(new Name("Suraj", "Teli"));
    }

    @GetMapping(path = "/persons/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionPersonByAcceptHeader() {
        return new PersonV1("Suraj Teli");
    }

    @GetMapping(path = "/persons/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionPersonBAcceptHeader() {
        return new PersonV2(new Name("Suraj", "Teli"));
    }

}
