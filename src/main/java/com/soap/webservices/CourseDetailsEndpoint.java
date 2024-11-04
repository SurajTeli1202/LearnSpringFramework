package com.soap.webservices;

import com.learnspringboot.courses.*;
import com.soap.exception.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;

    @PayloadRoot(namespace = "http://learnspringboot.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetails(@RequestPayload GetCourseDetailsRequest request){
        GetCourseDetailsResponse getCourseDetailsResponse = new GetCourseDetailsResponse();
        Course course = service.findById(request.getId());
        if (course == null) {
            throw new CourseNotFoundException("Invalid Course Id "+ request.getId());
        }
        CourseDetails courseDetails = getCourseDetails(course);
        getCourseDetailsResponse.setCourseDetails(courseDetails);
        return getCourseDetailsResponse;
    }

    private static CourseDetails getCourseDetails(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }

    @PayloadRoot(namespace = "http://learnspringboot.com/courses", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetails(@RequestPayload GetAllCourseDetailsRequest request){
        GetAllCourseDetailsResponse getAllCourseDetailsResponse = new GetAllCourseDetailsResponse();
        List<Course> courses = service.findAll();
        courses.stream().iterator().forEachRemaining(course ->
                getAllCourseDetailsResponse.getCourseDetails().add(getCourseDetails(course)));
        return getAllCourseDetailsResponse;
    }

    @PayloadRoot(namespace = "http://learnspring boot.com/courses", localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse deleteCourseDetails(@RequestPayload DeleteCourseDetailsRequest request){
        DeleteCourseDetailsResponse deleteCourseDetailsResponse = new DeleteCourseDetailsResponse();
        CourseDetailsService.Status status = service.deleteCourse(request.getId());
        deleteCourseDetailsResponse.setStatus(mapStatus(status));
        return deleteCourseDetailsResponse;
    }

    public Status mapStatus (CourseDetailsService.Status status) {
        if(status == CourseDetailsService.Status.FAILURE) {
            return Status.FAILURE;
        }
        return Status.SUCCESS;
    }
}
