package com.abc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.dao.StudentDAO;
import com.abc.factory.StudentDaoFactory;
import com.abc.model.Student;

@WebServlet("/updateData")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Collect the data from client
		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		String saddr = request.getParameter("saddr");
		String sage = request.getParameter("sage");
		String semail = request.getParameter("semail");

		// Create a DTO and send it to DAO Layer
		Student student = new Student();
		student.setSid(Integer.parseInt(sid));
		student.setSname(sname);
		student.setSaddr(saddr);
		student.setSage(Integer.parseInt(sage));
		student.setSemail(semail);

		// Communicate with DAO to get the data
		StudentDAO studentDao = StudentDaoFactory.getInstatnce();
		String status = studentDao.updateStudent(student);

		RequestDispatcher rd = null;

		if (status.equals("success")) {
			rd = request.getRequestDispatcher("/successUpdate.html");
			rd.forward(request, response);
		} else {
			rd = request.getRequestDispatcher("/failureUpdate.html");
			rd.forward(request, response);
		}
	}

}
