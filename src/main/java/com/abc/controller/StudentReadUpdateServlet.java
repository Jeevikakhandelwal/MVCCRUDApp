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

@WebServlet("/update")
public class StudentReadUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Collect sid from client
		String sid = request.getParameter("sid");
		
		// Communicate with DAO to get the data
		StudentDAO studentDao = StudentDaoFactory.getInstatnce();
		Student student = studentDao.findById(Integer.parseInt(sid));
		System.out.println(student);

		
		//Rendering the Student Object data to the response
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>OUTPUT</title></head>");
		out.println("<body bgcolor='pink'>");
		
		if (student != null) {
			out.println("<h1 style='color:green; text-align:center;'>STUDENT DETAILS</h1>");
			out.println("<form action='./updateData' method='post'>");
			out.println("<table border='1' align='center'>");
			out.println("<tr><td>SID</td><td>" + student.getSid() + "</td></tr>");
			out.println("<input type='hidden' name='sid' value='" + student.getSid() + "'/>");
			out.println("<tr><td>SNAME</td><td> <input type='text' name='sname' value='" + student.getSname() + "'</td></tr>");
			out.println("<tr><td>SAGE</td><td><input type='text' name='sage' value='" + student.getSage() + "'</td></tr>");
			out.println("<tr><td>SADDR</td><td><input type='text' name='saddr' value='" + student.getSaddr()+ "'</td></tr>");
			out.println("<tr><td>SEMAIL</td><td><input type='text' name='semail' value='" + student.getSemail() + "'</td></tr>");
			out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
			out.println("</table>");
			out.println("</form>");
		} else {
			out.println(
					"<h1 style='color:red; text-align:center;'>STUDENT RECORD NOT FOUND FOR THE ID : " + sid + "</h1>");
		}

		out.println("</body>");
		out.println("</html>");

		out.close();	
	}
  
}
