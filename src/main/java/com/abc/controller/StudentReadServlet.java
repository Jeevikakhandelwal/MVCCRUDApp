package com.abc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.dao.StudentDAO;
import com.abc.factory.StudentDaoFactory;
import com.abc.model.Student;

@WebServlet("/read")
public class StudentReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the parameters from the client
		String sid = request.getParameter("sid");

		// Communicate with DAO to get the data from database
		StudentDAO studentDao = StudentDaoFactory.getInstatnce();
		Student student = studentDao.findById(Integer.parseInt(sid));

		//Rendering the Student Object data to the response
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Student Registration Form</title></head>");
		out.println("<body align='center'>");
		

		if (student != null) {
			out.println("<h1 style='color:green; text-align:center;'>STUDENT DETAILS</h1>");
			out.println("<table border='1' align='center'>");
			out.println("<tr><td>SID</td><td>" + student.getSid() + "</td></tr>");
			out.println("<tr><td>SNAME</td><td>" + student.getSname() + "</td></tr>");
			out.println("<tr><td>SAGE</td><td>" + student.getSage() + "</td></tr>");
			out.println("<tr><td>SADDR</td><td>" + student.getSaddr() + "</td></tr>");
			out.println("<tr><td>SEMAIL</td><td>" + student.getSemail() + "</td></tr>");
			out.println("</table>");
		} else {
			out.println(
					"<h1 style='color:red; text-align:center;'>STUDENT RECORD NOT FOUND FOR THE ID : " + sid + "</h1>");
		}
		
		out.println("</body></html>");
		
		//Closing Stream
		out.close();	
	}

}
