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

@WebServlet("/create")
public class StudentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the parameters from the client
		String sname = request.getParameter("sname");
		String saddr = request.getParameter("saddr");
		String sage = request.getParameter("sage");
		String semail = request.getParameter("semail");

		// Create a DTO and inject these values
		Student student = new Student();
		student.setSname(sname);
		student.setSaddr(saddr);
		student.setSage(Integer.parseInt(sage));
		student.setSemail(semail);

		StudentDAO studentDao = StudentDaoFactory.getInstatnce();
		String status = studentDao.save(student);
		System.out.println(status);

		RequestDispatcher rd = null;
		if (status.equals("success")) {
			rd=request.getRequestDispatcher("/success.html");
			rd.forward(request, response);
		} else {
			rd=request.getRequestDispatcher("/failure.html");
			rd.forward(request, response);
		}
	}

}
