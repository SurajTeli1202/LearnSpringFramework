package com.learnJPAandHibernate.user;

import com.rest.webservices.users.User;
import com.springboot.learnspringboot.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface  UserSpringDataJPARepository extends JpaRepository<User, Long> {

    List<User> findByBirthDate(LocalDate birthDate);

    List<User> findByName(String name);
}
