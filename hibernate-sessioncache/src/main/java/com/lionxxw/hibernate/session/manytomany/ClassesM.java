package com.lionxxw.hibernate.session.manytomany;

import java.io.Serializable;
import java.util.Set;

public class ClassesM implements Serializable{
	private Long cid;
	private String name;
	private String description;
	
	private Set<StudentM> students; // 一对多

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

	public Set<StudentM> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentM> students) {
		this.students = students;
	}
}
