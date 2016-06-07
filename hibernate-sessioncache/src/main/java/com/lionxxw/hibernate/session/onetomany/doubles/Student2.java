package com.lionxxw.hibernate.session.onetomany.doubles;

import java.io.Serializable;

public class Student2 implements Serializable{
	private Long sid;
	private String name;
	private String description;
	private Classes2 classes;

	public Classes2 getClasses() {
		return classes;
	}

	public void setClasses(Classes2 classes) {
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
