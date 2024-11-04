package com.learnJPAandHibernate.course;


import com.springboot.learnspringboot.Course;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class CourseCommandLineRunner implements CommandLineRunner {

//    For JPA
//    @Autowired
//    private CourseJPARespository courseJPARespository;

//    For Spring Data JPA
    @Autowired
    private CourseSpringDataJPARepository courseSpringDataJPARepository;

    @Override
    public void run(String... args) throws Exception {
        //    For JPA
//        courseJPARespository.insert(new Course(1, "SpringBoot", "SRT"));
//        courseJPARespository.insert(new Course(2, "Spring", "Suraj"));
//        courseJPARespository.insert(new Course(3, "JPA", "Suraj Teli"));
//
//        courseJPARespository.deleteByID(2);
//
//        System.out.println(courseJPARespository.findById(1));
////        System.out.println(courseJPARespository.findByID(2));
//        System.out.println(courseJPARespository.findById(3));


        //    For Spring Data JPA
//        courseSpringDataJPARepository.save(new Course(1, "SpringBoot", "SRT"));
//        courseSpringDataJPARepository.save(new Course(2, "Spring", "Suraj"));
//        courseSpringDataJPARepository.save(new Course(3, "JPA", "Suraj Teli"));
//        courseSpringDataJPARepository.save(new Course(4, "Spring Data JPA", "Suraj Teli"));
//
//        System.out.println(courseSpringDataJPARepository.findAll());
//
//        courseSpringDataJPARepository.deleteById(2l);
//
//        System.out.println(courseSpringDataJPARepository.findById(1l));
////        System.out.println(courseJPARespository.findByID(2l));
//        System.out.println(courseSpringDataJPARepository.findById(3l));
//        System.out.println(courseSpringDataJPARepository.count());
//        System.out.println(courseSpringDataJPARepository.findByAuthor("SRT"));
//        System.out.println(courseSpringDataJPARepository.findByName("JPA"));
//
//

    }
}
