package com.abc.factory;

import com.abc.dao.StudentDAO;
import com.abc.dao.StudentDaoImpl;

public class StudentDaoFactory {

	private static StudentDAO studentDao;
	
	private StudentDaoFactory() {
		
	}

	public static StudentDAO getInstatnce() {

		if(studentDao == null) {
			studentDao = new StudentDaoImpl();
		}
		return studentDao;
	}

}
