package com.lionxxw.hibernate.session.onetoone;

import java.io.Serializable;

public class CourseOne implements Serializable{
	private Long cid;
	private String name;
	private String description;

	private PersonOne person;

	public PersonOne getPerson() {
		return person;
	}

	public void setPerson(PersonOne person) {
		this.person = person;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
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
