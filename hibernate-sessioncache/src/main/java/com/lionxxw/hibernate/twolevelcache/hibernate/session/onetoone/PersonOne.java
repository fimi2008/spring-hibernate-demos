package com.lionxxw.hibernate.twolevelcache.hibernate.session.onetoone;

import java.io.Serializable;
import java.util.Set;

public class PersonOne implements Serializable{
	private Long pid;
	private String name;
	private String description;

	private Set<CourseOne> courses;

	public Set<CourseOne> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseOne> courses) {
		this.courses = courses;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
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
