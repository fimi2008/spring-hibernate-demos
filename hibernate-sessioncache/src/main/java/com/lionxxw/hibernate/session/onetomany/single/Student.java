package com.lionxxw.hibernate.session.onetomany.single;

import java.io.Serializable;

public class Student implements Serializable{
	private Long sid;
	private String name;
	private String description;
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
