package com.learnJPAandHibernate.course.jdbc;

import com.springboot.learnspringboot.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseJDBCCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseJDBCRepository courseJDBCRepository;

    @Override
    public void run(String... args) throws Exception {

//        courseJDBCRepository.insert(new Course(5, "SpringBoot", "SRT"));
//        courseJDBCRepository.insert(new Course(6, "Spring", "Suraj"));
//        courseJDBCRepository.insert(new Course(7, "JPA", "Suraj Teli"));
//
//        courseJDBCRepository.deleteByID(6);
//
//        System.out.println(courseJDBCRepository.findByID(5));
////        System.out.println(courseJDBCRepository.findByID(2));
//        System.out.println(courseJDBCRepository.findByID(7));
    }
}
