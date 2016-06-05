package com.lionxxw.hibernate.session.domain;

import java.io.Serializable;

public class Person implements Serializable{
	private Long pid;
	private String name;
	private String sex;
	
	public Person(String name){
		this.name = name;
	}
	
	public Person(){}
	
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
