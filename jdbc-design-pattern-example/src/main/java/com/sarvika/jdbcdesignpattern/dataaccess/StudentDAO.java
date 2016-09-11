package com.sarvika.jdbcdesignpattern.dataaccess;

import java.util.List;

import com.sarvika.jdbcdesignpattern.common.IConvertibleDAO;
import com.sarvika.jdbcdesignpattern.domainmodel.Student;

public interface StudentDAO extends IConvertibleDAO<Student> {

	public List<Student> getByClassroom(int classroomid);
	
	public List<Student> getAll();
	
	public Student getStudent(int id);
	
	public void deleteStudent(Student student);
	
	public void saveStudent(Student student);
	
}
