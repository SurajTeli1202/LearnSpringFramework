package com.learnJPAandHibernate.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.webservices.users.User;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private int id;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Post(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
