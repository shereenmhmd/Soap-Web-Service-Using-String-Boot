package com.sheworks.soapdemo.soap.bean;

public class Course {

	private int id;
	private String name;
	private String Description;
	
	
	
	public Course(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		Description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", Description=" + Description + "]";
	}
	
	
}
