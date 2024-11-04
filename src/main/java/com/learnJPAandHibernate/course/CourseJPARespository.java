package com.learnJPAandHibernate.course;


import com.springboot.learnspringboot.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJPARespository {

    @PersistenceContext
    private EntityManager entityManager;

    public void insert(Course course){
        entityManager.merge(course);
    }
    public Course findById(long id){
        return entityManager.find(Course.class, id);
    }
    public void deleteByID(long id){
        Course course = findById(id);
        entityManager.remove(course);
    }

}
