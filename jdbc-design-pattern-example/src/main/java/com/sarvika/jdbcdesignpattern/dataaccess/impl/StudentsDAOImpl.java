package com.sarvika.jdbcdesignpattern.dataaccess.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sarvika.jdbcdesignpattern.common.DefaultDAO;
import com.sarvika.jdbcdesignpattern.dataaccess.StudentDAO;
import com.sarvika.jdbcdesignpattern.domainmodel.Student;

public class StudentsDAOImpl extends DefaultDAO implements StudentDAO {
	
	public List<Student> getByClassroom(int classroomid) {
		List<Student> students = new ArrayList<Student>();
		List<Map<String, Object>> result 
				= evictGet("SELECT * FROM STUDENTS WHERE CLASSROOMID = " + classroomid + ";");
		
		for (Map<String, Object> data : result) {
			students.add(mapToObject(data));
		}
		
		return students;
	}
	
	public Student getStudent(int id) {
		Map<String, Object> map = get("STUDENTS", id);
		
		if (map != null)
			return mapToObject(map);
		
		return null;
	}
	
	public List<Student> getAll() {

		List<Student> students = new ArrayList<Student>();
		List<Map<String, Object>> result = listAll("STUDENTS");
		
		for (Map<String, Object> data : result) {
			students.add(mapToObject(data));
		}
		
		return students;
	
	}

	public void deleteStudent(Student student) {
		delete("STUDENTS", student.getId());		
	}

	public void saveStudent(Student student) {
		save("STUDENTS", objectToMap(student));		
	}
	
	public Student mapToObject(Map<String, Object> map) {
		Student student = new Student();
		
		student.setClassroomid((Integer) map.get("classroomid"));
		student.setId((Integer) map.get("id"));
		student.setName((String) map.get("name"));
		
		return student;
	}

	public Map<String, Object> objectToMap(Student object) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("classroomid", object.getClassroomid());
		map.put("id", object.getId());
		map.put("name", object.getName());
		
		return map;
	}

}
