package com.learnJPAandHibernate.user.jpa;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.learnJPAandHibernate.post.Post;
import com.learnJPAandHibernate.post.PostSpringDataJPARepository;
import com.learnJPAandHibernate.user.UserSpringDataJPARepository;
import com.rest.webservices.users.User;
import com.rest.webservices.users.UserNotFoundException;
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
public class UserJPAResource {


    private UserSpringDataJPARepository repository;

    private PostSpringDataJPARepository postSpringDataJPARepository;

    public UserJPAResource(UserSpringDataJPARepository repository, PostSpringDataJPARepository postSpringDataJPARepository) {
        this.repository = repository;
        this.postSpringDataJPARepository = postSpringDataJPARepository;
    }

    @GetMapping( "/jpa/users")
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping( "/jpa/usersDF")
    public MappingJacksonValue getAllUsersForDynamicFiltering() {
        //uncomment @JsonFilter("DynamicFilter") from User class
        List<User> users = repository.findAll();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("user_name", "birthDate");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DynamicFilter", filter);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @GetMapping( "/jpa/users/{id}")
    public EntityModel<User> getUserById(@PathVariable int id) {
        User user = repository.findById(Long.valueOf(id)).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("id: "+id);
        }
        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));


        return entityModel;
    }
    @GetMapping( "/jpa/usersDF/{id}")
    public MappingJacksonValue getUserByIdForDynamicFiltering(@PathVariable int id) {
        //uncomment @JsonFilter("DynamicFilter") from User class
        User user = repository.findById(Long.valueOf(id)).orElse(null);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("user_name", "birthDate");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DynamicFilter", filter);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }


    @PostMapping("/jpa/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User newUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(newUser)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        repository.deleteById((long) id);
    }

    @PutMapping("/jpa/users/{id}")
    public ResponseEntity<User> saveUser(@PathVariable int id, @Valid @RequestBody User user) {
        user.setId(id);
        User newUser = repository.save(user);
        if (user == null) {
            throw new UserNotFoundException("id: "+id);
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(newUser)
                .toUri();
        return ResponseEntity.ok(newUser);
    }

    @GetMapping( "/jpa/users/{id}/posts")
    public List<Post> getPostByUserId(@PathVariable int id) {
        User user = repository.findById(Long.valueOf(id)).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("id: "+id);
        }
        return user.getPost();
    }

    @PostMapping( "/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        User user = repository.findById(Long.valueOf(id)).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("id: "+id);
        }
        post.setUser(user);
        Post savedPost = postSpringDataJPARepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
