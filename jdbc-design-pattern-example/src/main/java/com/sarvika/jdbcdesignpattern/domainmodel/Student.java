package com.sarvika.jdbcdesignpattern.domainmodel;

public class Student {

	private int id;
	private String name;
	private int classroomid;

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

	public int getClassroomid() {
		return classroomid;
	}

	public void setClassroomid(int classroomid) {
		this.classroomid = classroomid;
	}

}
