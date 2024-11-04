package com.rest.webservices.users;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {


    private UserDaoService service;

    public UserResource(UserDaoService userDaoService) {
        this.service = userDaoService;
    }

    @GetMapping( "/users")
    public List<User> getAllUsers() {
        return service.findAllUsers();
    }

    @GetMapping( "/usersDF")
    public MappingJacksonValue getAllUsersForDynamicFiltering() {
        //uncomment @JsonFilter("DynamicFilter") from User class
        List<User> users = service.findAllUsers();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("user_name", "birthDate");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DynamicFilter", filter);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @GetMapping( "/users/{id}")
    public EntityModel<User> getUserById(@PathVariable int id) {
        User user = service.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException("id: "+id);
        }
        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));


        return entityModel;
    }
    @GetMapping( "/usersDF/{id}")
    public MappingJacksonValue getUserByIdForDynamicFiltering(@PathVariable int id) {
        //uncomment @JsonFilter("DynamicFilter") from User class
        User user = service.findUserById(id);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("user_name", "birthDate");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DynamicFilter", filter);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }


    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User newUser = service.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(newUser)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteUser(id);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> saveUser(@PathVariable int id, @Valid @RequestBody User user) {
        User newUser = service.updateUserById(id, user);
        if (user == null) {
            throw new UserNotFoundException("id: "+id);
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(newUser)
                .toUri();
        return ResponseEntity.ok(newUser);
    }

}
