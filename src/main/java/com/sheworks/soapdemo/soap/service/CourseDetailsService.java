package com.sheworks.soapdemo.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sheworks.soapdemo.soap.bean.Course;

@Component
public class CourseDetailsService {
	
	public enum Status{
		SUCCESS,FAILURE
	}
	
	private static List<Course> courses = new ArrayList<>();
	
	static {
		Course c1 = new Course(1, "Spring Boot First Step!", "Spring boot Book");
		courses.add(c1);
		
		Course c2 = new Course(2, "Microservices First Step!", "Microservices Book");
		courses.add(c2);
		
		Course c3 = new Course(3, "Spring MVC First Step!", "Spring MVC Book");
		courses.add(c3);
		
		Course c4 = new Course(4, "Web Services First Step!", "Web Services Book");
		courses.add(c4);
		
		Course c5 = new Course(5, "Docker First Step!", "Docker Book");
		courses.add(c5);
	}
	public Course getById(int id){
		
		for(Course c : courses) {
			if(c.getId() == id)
				return c;	
		}
		
		return null;
		
	}
	
	public List<Course> getAll(){
			return courses;
			
		}
	
	
	public Status deleteCourse(int id){
		
		Iterator<Course> iterator = courses.iterator();
		
		while(iterator.hasNext()) {
			Course course = iterator.next();
			if(course.getId() == id){
				iterator.remove();
				return Status.SUCCESS;
			}
		}
		
		return Status.FAILURE;
		
	}

}
