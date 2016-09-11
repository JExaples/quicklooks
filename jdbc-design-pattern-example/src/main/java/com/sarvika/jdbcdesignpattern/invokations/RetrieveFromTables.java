package com.sarvika.jdbcdesignpattern.invokations;

import com.sarvika.jdbcdesignpattern.dataaccess.StudentDAO;
import com.sarvika.jdbcdesignpattern.dataaccess.impl.StudentsDAOImpl;
import com.sarvika.jdbcdesignpattern.domainmodel.Student;

public class RetrieveFromTables {

	public static void main(String[] args) {
		StudentDAO dao = new StudentsDAOImpl();
		
		Student student = new Student();
		student.setName("Jay");
		student.setId(1);
		student.setClassroomid(2);
		
		dao.saveStudent(student);
		
		System.out.println(dao.getAll());
	}
	
}
