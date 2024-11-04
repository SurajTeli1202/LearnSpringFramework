package com.soap.webservices;

import com.learnspringboot.courses.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseDetailsService {
    public enum Status {
        SUCCESS, FAILURE;
    }
    public static List<Course> courses = new ArrayList<>();
    static {
        Course course1 = new Course(1, "course1", "10 steps");
        courses.add(course1);

        Course course2 = new Course(2, "course2", "10 example");
        courses.add(course2);

        Course course3 = new Course(3, "course3", "10 students");
        courses.add(course3);

        Course course4 = new Course(4, "course4", "soap");
        courses.add(course4);
    }

    public Course findById(int id){
        return courses.stream().filter(course -> course.getId() == id).findFirst().orElse(null);
    }

    public List<Course> findAll() {
        return courses;
    }

    public Status deleteCourse(int id) {
        Course course = findById(id);
        if (course != null) {
            courses.remove(course);
            return Status.SUCCESS;
        }
        return Status.FAILURE;
    }



}
