package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.abc.model.Student;
import com.abc.utility.JdbcUtil;

public class StudentDaoImpl implements StudentDAO {

	@Override
	public String save(Student student) {

		System.out.println(student);
		try {
			Connection connection = JdbcUtil.getInstance();

			String SQL_INSERT_QUERY = "INSERT INTO STUDENT(SNAME,SAGE,SADDRESS,SEMAIL) VALUES(?,?,?,?)";

			PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT_QUERY);

			pstmt.setString(1, student.getSname());
			pstmt.setInt(2, student.getSage());
			pstmt.setString(3, student.getSaddr());
			pstmt.setString(4, student.getSemail());

			int rowCount = pstmt.executeUpdate();
			if (rowCount > 0) {
				return "success";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public Student findById(Integer sid) {
		try {
			Connection connection = JdbcUtil.getInstance();

			String SQL_INSERT_QUERY = "SELECT SID, SNAME, SAGE, SADDRESS, SEMAIL FROM STUDENT WHERE SID=?";

			PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT_QUERY);

			pstmt.setInt(1, sid);

			ResultSet resultSet = pstmt.executeQuery();
			
			if(resultSet!=null) {
				if(resultSet.next()) {
					Student student=new Student();
					student.setSid(resultSet.getInt(1));
					student.setSname(resultSet.getString(2));
					student.setSage(resultSet.getInt(3));
					student.setSaddr(resultSet.getString(4));
					student.setSemail(resultSet.getString(5));
					
					return student;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public String deleteById(Integer sid) {
		try {
			Connection connection = JdbcUtil.getInstance();

			String SQL_DELETE_QUERY = "DELETE FROM STUDENT WHERE SID= ? ";

			PreparedStatement pstmt = connection.prepareStatement(SQL_DELETE_QUERY);
			pstmt.setInt(1, sid);

			int rowCount = pstmt.executeUpdate();
			if (rowCount > 0) {
				return "success";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "failure";
	}
	
	@Override
	public String updateStudent(Student student) {

		System.out.println(student);

		try {
			Connection connection = JdbcUtil.getInstance();

			String SQL_UPDATE_QUERY = "update student set sname=?,sage=?,saddress=?,semail=? where sid=?";

			PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_QUERY);

			pstmt.setString(1, student.getSname());
			pstmt.setInt(2, student.getSage());
			pstmt.setString(3, student.getSaddr());
			pstmt.setString(4, student.getSemail());
			pstmt.setInt(5, student.getSid());

			int rowCount = pstmt.executeUpdate();
			if (rowCount > 0) {
				return "success";
			}

		} catch ( SQLException e) {
			e.printStackTrace();
		}

		return "failure";
	}
}
