package com.lionxxw.hibernate.twolevelcache.hibernate.session.manytomany;

import java.io.Serializable;
import java.util.Set;

public class StudentM implements Serializable{
	private Long sid;
	private String name;
	private String description;
	private ClassesM classes;
	private Set<Course> courses;  // 课程

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public ClassesM getClasses() {
		return classes;
	}

	public void setClasses(ClassesM classes) {
		this.classes = classes;
	}

	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
