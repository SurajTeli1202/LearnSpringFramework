package com.rest.webservices.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.learnJPAandHibernate.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.List;

//@JsonFilter("DynamicFilter")
@Entity(name = "user_details")
public class User {
    //@JsonIgnore
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=2, message = "Name should have atleast 2 characters.")
    @JsonProperty("user_name")
    private String name;
    @Column
    @Past(message = "Birth Date should be in past.")
    private LocalDate birthDate;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Post> post;

    public User(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
