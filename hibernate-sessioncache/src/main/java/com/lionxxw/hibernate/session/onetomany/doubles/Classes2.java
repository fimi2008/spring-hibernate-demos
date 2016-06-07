package com.lionxxw.hibernate.session.onetomany.doubles;

import java.io.Serializable;
import java.util.Set;

public class Classes2 implements Serializable{
	private Long cid;
	private String name;
	private String description;
	
	private Set<Student2> students; // 一对多

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

	public Set<Student2> getStudents() {
		return students;
	}

	public void setStudents(Set<Student2> students) {
		this.students = students;
	}
}
