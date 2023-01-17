package com.abc.dao;

import com.abc.model.Student;

public interface StudentDAO {
	
	public String save(Student student) ;
	
	public Student findById(Integer sid);
	
	public String deleteById(Integer sid);
	
	public String updateStudent(Student student);
}
