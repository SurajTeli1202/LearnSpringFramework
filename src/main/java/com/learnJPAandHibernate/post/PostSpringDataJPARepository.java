package com.learnJPAandHibernate.post;

import com.rest.webservices.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PostSpringDataJPARepository extends JpaRepository<Post, Long> {

    List<Post> findByUser(User id);

    List<Post> findByDescription(String name);
}
