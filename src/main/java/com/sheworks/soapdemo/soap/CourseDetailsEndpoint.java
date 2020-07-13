package com.sheworks.soapdemo.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.shewebservices.courses.CourseDetails;
import com.shewebservices.courses.GetAllCourseDetailsRequest;
import com.shewebservices.courses.GetAllCourseDetailsResponse;
import com.shewebservices.courses.GetCourseDetailsRequest;
import com.shewebservices.courses.GetCourseDetailsResponse;
import com.shewebservices.courses.RemoveCourseDetailsRequest;
import com.shewebservices.courses.RemoveCourseDetailsResponse;
import com.shewebservices.courses.RemoveStatus;
import com.sheworks.soapdemo.soap.bean.Course;
import com.sheworks.soapdemo.soap.exception.CourseNotFoundException;
import com.sheworks.soapdemo.soap.service.CourseDetailsService;
import com.sheworks.soapdemo.soap.service.CourseDetailsService.Status;

@Endpoint

public class CourseDetailsEndpoint {

	@Autowired
	CourseDetailsService service;

	@PayloadRoot(namespace = "http://shewebservices.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload // converts java response to XML
	// @RequestPayload converts XML request to java
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) throws CourseNotFoundException {

		Course course = service.getById(request.getId());
		
		if(course == null)
			throw new CourseNotFoundException("Invalid Course Id "+request.getId());
		return mapCourseDeatils(course);

	}

	@PayloadRoot(namespace = "http://shewebservices.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload // converts java response to XML
	// @RequestPayload converts XML request to java
	public GetAllCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {

		List<Course> courses = service.getAll();
		return mapAllCourseDeatils(courses);

	}

	@PayloadRoot(namespace = "http://shewebservices.com/courses", localPart = "RemoveCourseDetailsRequest")
	@ResponsePayload // converts java response to XML
	// @RequestPayload converts XML request to java
	public RemoveCourseDetailsResponse processCourseDetailsRequest(@RequestPayload RemoveCourseDetailsRequest request) {

		RemoveCourseDetailsResponse response = new RemoveCourseDetailsResponse();
		
		Status status= service.deleteCourse(request.getId());
		
		response.setRemovestatus(mapStatus(status));
		return response;
	}
	
	// maps java status to XML status
	private RemoveStatus mapStatus(Status status) {
		if(Status.FAILURE == status )
			return RemoveStatus.FAILURE;
		else
		return RemoveStatus.SUCCESS;
	}

	private GetCourseDetailsResponse mapCourseDeatils(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		CourseDetails courseDetails = mapCourse(course);
		response.setCourseDetails(courseDetails);
		return response;
	}

	private GetAllCourseDetailsResponse mapAllCourseDeatils(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();

		for (Course course : courses) {
			CourseDetails courseDetails = mapCourse(course);
			response.getCourseDetails().add(courseDetails);
		}
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
}
