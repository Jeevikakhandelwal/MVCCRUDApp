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

@WebServlet("/delete")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Collect the input from client
		String sid = request.getParameter("sid");
			
		//Get the information from DAO layer
		StudentDAO studentDao = StudentDaoFactory.getInstatnce();
		String status = studentDao.deleteById(Integer.parseInt(sid));
		
		//WebComponent Communication 
				RequestDispatcher rd = null;
				if (status.equals("success")) {
					rd = request.getRequestDispatcher("/recorddelete.html");
					rd.forward(request, response);
				} else {
					rd = request.getRequestDispatcher("/recordNotAvailable.html");
					rd.forward(request, response);
				}
	}

}
